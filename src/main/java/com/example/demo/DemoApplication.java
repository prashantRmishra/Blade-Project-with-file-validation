 
package com.example.demo;

import com.blade.Blade;

public class DemoApplication {

	public static void main(String[] args) {
		Blade.of().get("/home", var->var.text("Hello 12322")).start(DemoApplication.class, args);
	}

}