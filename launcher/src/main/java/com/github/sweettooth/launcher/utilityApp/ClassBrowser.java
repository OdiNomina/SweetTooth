package com.github.sweettooth.launcher.utilityApp;

import java.util.Arrays;

import com.github.sweettooth.controller.api.ControllerFactory;
import com.github.sweettooth.controller.api.ControllerInterface;
import com.github.sweettooth.launcher.app.SweetTooth;
import com.github.sweettooth.model.api.EventFactory;
import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.api.Interrogable;
import com.github.sweettooth.model.api.LocationInterface;
import com.github.sweettooth.model.api.Observer;
import com.github.sweettooth.model.api.Playable;
import com.github.sweettooth.model.api.Processable;
import com.github.sweettooth.model.api.Settings;
import com.github.sweettooth.model.api.SnackFactory;
import com.github.sweettooth.model.api.Snackable;
import com.github.sweettooth.model.api.Subject;
import com.github.sweettooth.view.api.DisplayElement;
import com.github.sweettooth.view.api.DisplayFactory;

public class ClassBrowser {
	public static void main(String... args) {
		System.getProperties().list(System.out);
		System.out.println();
		System.out.println();
		
		// --- launcher
		System.out.println("### Modul: launcher\n");
		Class<?>[] launcher = {SweetTooth.class};
		show(launcher);
		
		// --- controller
		System.out.println("### Modul: controller\n");
		Class<?>[] controller = {ControllerInterface.class, ControllerFactory.class};
		show(controller);
		
		// --- view
		System.out.println("### Modul: view\n");
		Class<?>[] view = {DisplayElement.class, DisplayFactory.class};
		show(view);
		
		// --- model
		System.out.println("### Modul: model\n");
		Class<?>[] model = {EventFactory.class, GameModelInterface.class, Interrogable.class, LocationInterface.class, Observer.class, Playable.class,
				Processable.class, Settings.class, Snackable.class, SnackFactory.class, Subject.class};
		show(model);
	}
	
	private static void show(Class<?>[] classes) {
		for(Class<?> c : classes) {
			System.out.println(c);
			System.out.println("# Superclass: " + c.getSuperclass());
			System.out.println("# Declared fields:");
			Arrays.asList(c.getDeclaredFields()).forEach(action -> System.out.println(action));
			System.out.println("# Declared constructors:");
			Arrays.asList(c.getDeclaredConstructors()).forEach(action -> System.out.println(action));
			System.out.println("# Declared methods:");
			Arrays.asList(c.getDeclaredMethods()).forEach(action -> System.out.println(action));
			System.out.println();
		}
	}
}
