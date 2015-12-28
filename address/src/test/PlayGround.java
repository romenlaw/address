package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import org.junit.Test;

@FunctionalInterface
interface Converter<F, T> {
	T convert(F from);
	default String converterSays(Object s) {
		return "Converter Says: "+s;
	}
}

public class PlayGround {

	@Test
	public void defMethod() {
		Converter conv=new Converter() {

			@Override
			public Object convert(Object from) {
				return from.toString();
			}	
		};
		System.out.println("Default Method of Interface: "+conv.convert(321));
		System.out.println("Default Method of Interface: "+conv.converterSays("321f"));
	}
	
	@Test
	public void lamda() {
		List<String> names=Arrays.asList("Peter", "anna", "mike", "Xenia");	
		Collections.sort(names, (a,b)->a.compareToIgnoreCase(b));
		System.out.println("Lamda: "+names);
	}
	
	static int outerStatic;
	int outerNum;
	@Test
	public void funcInterface() {
		final String s="00";
		Converter<String, Integer> converter=(f)-> {
			try{
				outerStatic=1;
				outerNum=2;
				return Integer.valueOf(f+s+outerStatic+outerNum);
			} catch (NumberFormatException e) {
				return -1;
			}
			};
		Integer converted=converter.convert("123");
		System.out.println("Functional Interface: "+converted);
		outerStatic=8;
		outerNum=9;
	}
	
	public static String simonSays(Object o) {
		return "Simon says: "+o;
	}
	public static String romenSays(Object o) {
		return "Romen says: "+o;
	}
	@Test
	public void staticMethodRef() {
		Converter<Object, String> converter=PlayGround::simonSays;
		System.out.println("Static Method Reference: "+converter.convert(123f));
		converter=PlayGround::romenSays;
		System.out.println("Static Method Reference: "+converter.convert("Happy & Prosperous 2016!"));
		
		// this will not compile, as default method is not accessible in lamda
		// Converter conv = (f) -> converterSays(f);
	}
	
	String name="default name";
	@Test
	public void testOptional() {
		PlayGround pg=new PlayGround();
		pg.name="new name";
		
		Optional<PlayGround> opt=Optional.ofNullable(null);
		System.out.println("Optional: "+opt.orElse(pg).name);
		
		opt=Optional.ofNullable(new PlayGround());
		System.out.println("Optional: "+opt.orElse(pg).name);
	}
	
	@Test
	public void testStream() {
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("12.6C");
		stringCollection.add("89F");
		stringCollection.add("-0C");
		stringCollection.add("31C");
		stringCollection.add("-60F");
		stringCollection.add("65F");
		stringCollection.add("90F");
		stringCollection.add("28C");
		
		System.out.print("Stream: celcius:");
		stringCollection.stream()
		.filter(a -> a.contains("C"))
		.sorted()
		.forEach(System.out::print);;
		
		System.out.print("\nStream: convert to celcius:");
		stringCollection.stream()
		.map(s -> {
			Float t=0f;
			if(s!=null) {
				if(s.contains("C"))
					t=Float.valueOf(s.replace("C", ""));
				else
					t=(float) ((Float.valueOf(s.replace("F", "")) - 32) / 1.8);
			}
			return t;
		}).sorted()
		.forEach(System.out::println);
		
		
		Optional<Float> total=stringCollection.stream()
		.map(s -> {
			Float t=0f;
			if(s!=null) {
				if(s.contains("C"))
					t=Float.valueOf(s.replace("C", ""));
				else
					t=(float) ((Float.valueOf(s.replace("F", "")) - 32) / 1.8);
			}
			return t;
		}).reduce((t1, t2) -> t1+t2);
		System.out.print("\nStream: reduce: find total: ");
		total.ifPresent(System.out::println);
		
		OptionalDouble average=stringCollection.stream()
		.mapToDouble(s -> {
			Double t=0d;
			if(s!=null) {
				if(s.contains("C"))
					t=Double.valueOf(s.replace("C", ""));
				else
					t=(double) ((Double.valueOf(s.replace("F", "")) - 32) / 1.8);
			}
			return t;
		}).average();
		System.out.print("Stream: find average: ");
		average.ifPresent(System.out::println);
	}
}
