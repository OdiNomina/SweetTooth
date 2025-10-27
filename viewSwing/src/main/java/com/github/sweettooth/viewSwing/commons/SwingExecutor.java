package com.github.sweettooth.viewSwing.commons;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

import com.github.sweettooth.shared.logging.Loggable;

/**
 * <pre>
 * Example usage:
 *
 * // Runnable
 * SwingExecutor.getInstance().submit(
 * 	    () -> doHeavyWork(),			// runnable in background thread
 *	    () -> label.setText("Ready!")	// runnable in EDT upon completion.
 *	);
 *
 * // Callable
 * SwingExecutor.getInstance().submit(
 * 	    () -> { doHeavyWork(); return "result"; },			// callable with result in background thread
 * 	    result -> label.setText("Ready: " + result),		// method accept of consumer in EDT on success
 * 	    ex -> label.setText("Error: " + ex.getMessage())	// method accept of consumer in EDT on error
 * 	);
 * 
 * // Callable abbreviated
 * SwingExecutor.getInstance().submit(
 *  	this::readFile,
 *  	this::updateUI
 *  	this::handleError
 *  );
 *  
 *  // Shutdown of the thread pool
 *  // Wait up to 2 seconds for all background tasks to finish before the app is actually closed.
 *  SwingExecutor.getInstance().shutdownAndAwait(2, TimeUnit.SECONDS);
 *  </pre>
 */

public class SwingExecutor implements Loggable {
	
	private static volatile SwingExecutor instance;
	
    /**
     * <pre>
     * Returns the global SwingExecutor instance (Singleton).
     * Default thread pool size: 2
     * {@code customThreadCount()} must be called before to obtain a SwingExecutor instance with a user-defined number of threads.
     * </pre>
     */
    public static SwingExecutor getInstance() {
        if (instance == null) {
            synchronized (SwingExecutor.class) {
                if (instance == null) // Double-Checked Locking
                    instance = new SwingExecutor(2);
            }
        }
        return instance;
    }
    
    /*
     * Alternative Herangehensweise mit Nutzung der Atomic* Klassen
     * 
    private static AtomicReference<SwingExecutor> atomicInstance = new AtomicReference<>();
    
    public static SwingExecutor getAtomicInstance() {
    	SwingExecutor instance = atomicInstance.get();
        if (instance == null) {
        	SwingExecutor newInstance = new SwingExecutor(2);
            if (!atomicInstance.compareAndSet(null, newInstance))
                instance = atomicInstance.get();
        }
        return instance;
    }
    */

    /**
     * <pre>
     * Creates a SwingExecutor with a custom thread count.
     * Must be called before {@code getInstance()} to take effect.
     * </pre>
     * @param numberOfThreads number of background threads
     */
    public static void customThreadCount(int numberOfThreads) {
    	synchronized(SwingExecutor.class) {
    		if (instance != null)
    			throw new IllegalStateException("SwingExecutor already initialized");

    		instance = new SwingExecutor(numberOfThreads);
        }
    }
    
    private final Logger logger;
	private final ExecutorService executorService;

    private SwingExecutor(int numberOfThreads) {
    	logger = Logger.getLogger(SwingExecutor.class.getName());
        executorService = Executors.newFixedThreadPool(numberOfThreads);
    }
    
    @Override
	public Logger getLogger() {
    	return logger;
	}

	/**
	 * Executes a runnable task in the background and optionally performs a callback in the EDT upon completion.
	 * @param task runnable
	 * @param onDone runnable in the EDT after completion
	 */
	public Future<?> submit(Runnable task, Runnable onDone) {
		return executorService.submit( () -> {
				try {
		            task.run();
		            if (onDone != null)
		                SwingUtilities.invokeLater(onDone);
				}
				catch(Throwable ex) {
					error("Error while executing a runnable task in the background.", ex);
				}
	        });
	}
	
	/**
	 * Executes a runnable task in the background.
	 * @param task runnable fire-and-forget
	 */
	public Future<?> submit(Runnable task) {
		return executorService.submit( () -> {
				try {
		            task.run();
				}
				catch(Throwable ex) {
					error("Error while executing a runnable task in the background.", ex);
				}
			});
	}

	/**
     * Executes a callable task in the background and optionally performs a callback in the EDT with the result upon completion.
     * @param task callable
     * @param onSuccess after completion this consumer is executed in the EDT
     * @param onError if an exception occurs, this consumer is executed in the EDT
     * @param <T> return type of the callable task
     */
    public <T> Future<?> submit(Callable<T> task, Consumer<T> onSuccess, Consumer<Throwable> onError) {
        return executorService.submit( () -> {
	            try {
	                T result = task.call();
	                if(onSuccess != null)
	                	SwingUtilities.invokeLater( () -> onSuccess.accept(result) );
	            }
	            catch (Throwable ex) {
	                if (onError != null)
	                    SwingUtilities.invokeLater( () -> onError.accept(ex) );
	                else
	                	error("Error while executing a callable task in the background.", ex);
	            }
	        });
    }
    
    /**
     * Executes a callable task in the background and optionally performs a callback in the EDT with the result upon completion.
     * @param task callable
     * @param onSuccess after completion this consumer is executed in the EDT
     * @param <T> return type of the callable task
     */
    public <T> Future<?> submit(Callable<T> task, Consumer<T> onSuccess) {
        return executorService.submit( () -> {
	            try {
	                T result = task.call();
	                if(onSuccess != null)
	                	SwingUtilities.invokeLater( () -> onSuccess.accept(result) );
	            }
	            catch (Throwable ex) {
	            	error("Error while executing a callable task in the background.", ex);
	            }
	        });
    }
    
    /**
     * Shuts down the executor and waits until all tasks are completed or the timeout is reached.
     * Called by EDT: performs the shutdown in a separate daemon thread so that the GUI is not blocked.
     * @param timeout maximum wait time
     * @param unit time unit
     */
    public void shutdownAndAwait(long timeout, TimeUnit unit) {
    	if (SwingUtilities.isEventDispatchThread()) { // Nicht im EDT blockieren!
    		Thread shutdownThread = new Thread( () -> shutdownAndAwait(timeout, unit), "SwingExecutor-Shutdown" );
    		shutdownThread.setDaemon(true);
    		shutdownThread.start();
            return;
        }
    	
    	info("ExecutorService (SwingExecutor) is being shut down.");
    	executorService.shutdown();
        try {
            if (!executorService.awaitTermination(timeout, unit)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
        	error("Error while shut down ExecutorService", ex);
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
