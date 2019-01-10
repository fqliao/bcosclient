package org.client;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListenerExample implements NativeKeyListener {
	public void nativeKeyPressed(NativeKeyEvent e) {

		if (e.getKeyCode() == NativeKeyEvent.VC_UP)
		{
			System.out.println("UP" + NativeKeyEvent.getKeyText(e.getKeyCode()));
		}
		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			try {
				GlobalScreen.unregisterNativeHook();
			} catch (NativeHookException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
//		System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
//		System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	public static void main(String[] args) {
		
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.WARNING);

		// Don't forget to disable the parent handlers.
		logger.setUseParentHandlers(false);
		
		Scanner sc = new Scanner(System.in);
		String nextLine = sc.nextLine();
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new GlobalKeyListenerExample());
	}
}
