package org.pa.lang.util;

import static java.lang.String.format;

import java.util.Collection;

public final class Validation {

	private Validation() {
	}

	public static <E> E notNull(E value, String formattedMsg, Object arg)
			throws IllegalArgumentException {
		if (value == null) {
			throw new IllegalArgumentException(format(formattedMsg, arg));
		}
		return value;
	}

	public static <E> E notNull(E value, String name)
			throws IllegalArgumentException {
		return notNull(value, "%s must not be null", name);
	}

	public static <E extends CharSequence> E notEmpty(E value,
			String formattedMsg, Object arg) throws IllegalArgumentException  {
		if (value == null || value.length() == 0) {
			formattedMsg = formattedMsg != null ? formattedMsg : "%s must not be null";
			arg = arg != null ? arg : "value";
			throw new IllegalArgumentException(format(formattedMsg, arg));
		}
		return value;
	}
	
	public static <E extends CharSequence> E notEmpty(E value, String name)
			throws IllegalArgumentException {
		return notEmpty(value, "%s must not be empty nor null", name);
	}

	public static <T, E extends Collection<T>> E notEmpty(E value,
			String formattedMsg, Object arg) throws IllegalArgumentException {
		if (value == null || value.isEmpty()) {
			formattedMsg = formattedMsg != null ? formattedMsg : "%s must not be null nor empty";
			arg = arg != null ? arg : "value";
			throw new IllegalArgumentException(format(formattedMsg, arg));
		}
		return value;
	}

	public static <T, E extends Collection<T>> E notEmpty(E value, String name)
			throws IllegalArgumentException {
		return notEmpty(value, "%s must not be empty nor null", name);
	}
	
	public static void isTrue(boolean condition, String formattedMsg, Object arg)
			throws IllegalArgumentException {
		if (!condition) {
			formattedMsg = formattedMsg != null ? formattedMsg
					: "illegal value: %s";
			throw new IllegalArgumentException(format(formattedMsg, arg));
		}
	}

}
