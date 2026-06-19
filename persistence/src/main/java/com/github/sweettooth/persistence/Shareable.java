package com.github.sweettooth.persistence;

import com.github.sweettooth.persistence.api.sharedDAO.ScoreEntry;

/**
 *Sealed marker interface
 */
public sealed interface Shareable 
	permits ScoreEntry
{}
