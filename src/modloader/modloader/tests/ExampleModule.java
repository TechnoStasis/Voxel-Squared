package modloader.tests;

import modloader.api.Mod;

@Mod(name = "testVonTickles", shouldInit = true)
public class ExampleModule {

	@Mod.Init
	public static void init()
	{
		System.out.println("Test Von Tickles 1.0 Initializing...");
	}
	
}
