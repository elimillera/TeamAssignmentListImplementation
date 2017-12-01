import java.lang.reflect.Constructor;

/**
 * Example of a framework for testing multiple implementations of the
 * ListADT for TEAM_TestListImplementations assignment.
 * 
 * @version 0.0
 * @author deppeler
 *
 */
public class Test_ListImplementations {
	static String errmsg = "Test failed: ";

	/**
	 * Run all tests for each list submission available.
	 * 
	 * @param args UNUSED
	 */
	public static void main(String[] args) {
		String [] ta_list = { "Deb", "Mingi", "Yash", "Sonu", 
						"Sapan", "Tianrun", "Roshan" };
		for ( String ta_name : ta_list ) { 
			String listClassName = "List_"+ta_name;
			System.out.println("\n==================== TESTING "+listClassName );
			runTestsFor(listClassName);
			System.out.println("==================== done");
		}
	}

	/**
	 * Constructs a list of the correct type based on the name provided.
	 * This method assumes there is a class with the name List_taName
	 * that implements the ListADT<T> type given.
	 * 
	 * For example: if taName is Deb, then it constructs an instance of
	 * a generic class with the following structure.
	 * 
	 *  <pre>public class List_Deb<T> implements ListADT<T> { ... }</pre>
	 * 
	 * @param listClassName The name "List_Deb" or other list type name.
	 */
	private static void runTestsFor(String listClassName) {
		try {
			ListADT<String> list = constructListOfString(listClassName);
			runTestsOn(listClassName,list);
		} catch (IllegalArgumentException e) {
			System.out.println("Unable to construct "+listClassName+" NO TESTS RUN");
		}
	}

	/**
	 * Constructs an instance of the list type that will contains String data.
	 * The class name is given and it is assumed to be a generic type with
	 * a no-arg constructor. If the List of Strings can not be constructed
	 * an IllegalArgumentException is thrown.
	 * 
	 * @param listname List_Deb or other that matches existing list type
	 * @return a ListADT<String> constructed with specified class name.
	 * 
	 * @throws IllegalArgumentException if any of several types of exceptions 
	 * occur when attempting to construct a ListADT<String> object from 
	 * the given class name. 
	 */
	private static ListADT<String> constructListOfString(String listname) {
		try {
			Class<?> listClassName = Class.forName(listname);
			Constructor<?> ctr = listClassName.getDeclaredConstructor();
			Object [] initargs = new Object[] { };

			// CAUTION: Type cast warnings are suppressed here.
			// to constructing Lists that will contain String data items.
			@SuppressWarnings("unchecked")  
			ListADT<String> list = ((ListADT<String>) ctr.newInstance(initargs));
			return list;
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to construct instance of "+listname+"<String>()");
		}
	}

	/**
	 * Runs all tests on this list.  Note, may have to create new lists 
	 * to be sure that we start with a new empty list instance.  
	 * Otherwise, errors from one test may affect the next test.
	 * 
	 * @param className List_Deb or other list class name
	 * @param list 
	 */
	private static void runTestsOn(String className,ListADT<String> list) {


		if ( ! list.isEmpty() ) { 
			System.out.println("test00 failed: "+className+" should be empty at start. ");
		}

		if ( list.size() != 0 ) { 
			System.out.println("test00 failed: "+className+" size() should be 0 at start, but size()="+ list.size());
		}
		
		//Check add
		test01_add_item(className,list);
		//Check size and isEmpty for 1 and false.
		test02_size_test(className,list);
		test03_isEmpty_test(className,list);
		
		list = constructListOfString(className);
		//NEW ARRAY
		//Add 100 items to list.
		test04_add_100_items(className, list);
		//Check size for 100
		test05_size_is101(className,list);
		//remove 50-100.
		test06_remove_last_51(className,list);
		//check size for 50.
		test07_size_is50(className,list);
		//check isEmpty for false.
		test08_isEmpty_false(className,list);
		//remove 0-49.
		test09_remove_first_50(className,list);
		//Check size is 0 and isEmpty True.
		test10_size_is0(className,list);
		test11_isEmpty_true(className,list);
		
		list = constructListOfString(className);
		//NEW ARRAY
		//Add abc and verify their correct placement with get.
		test12_add_abc(className,list);
		test13_check_get_abc(className,list);
		//Check contains for a,b,and c
		test14_check_contains_abc(className,list);
		//Check add with pos with def.
		test15_check_add_wpos(className,list);
		//check get for def.
		test16_check_get_def(className,list);
		test17_check_contains_def(className,list);
		//check remove returns item. remove end.
		test18_check_remove_def(className,list);
		//check remove returns item. remove beginning.
		test19_check_remove_abc(className,list);
		
	}
	
	/**
	 * tests add(item)
	 * @param className
	 * @param list
	 */
	private static void test01_add_item(String className, ListADT<String> list) {
		try{
			list.add("item");
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("01");
			System.out.println("unable to add item to list in: " + className);
		}
	}

	/**
	 * tests size()
	 * @param className
	 * @param list
	 */
	private static void test02_size_test(String className, ListADT<String> list) {
		try{
			if(list.size() != 1) throw new Exception();
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("02");
			System.out.println("wrong size returned in: " + className);
			System.out.println("should have returned 1, instead returned: " + list.size());
		}
	}
	
	/** 
	 * tests isEmpty()
	 * @param className
	 * @param list
	 */
	private static void test03_isEmpty_test(String className, ListADT<String> list) {
		try{
			if(list.isEmpty()) throw new Exception();
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("03");
			System.out.println("isEmpty() incorrectly returned true in: " + className);
		}
	}

	/**
	 * tests add for 100 items
	 * @param className
	 * @param list
	 */
	private static void test04_add_100_items(String className, ListADT<String> list) {
		try{
			for(int i = 0; i < 100; i++){
				list.add(String.valueOf(i));
			}
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("04");
			System.out.println("unable to add 100 items to list in: " + className);
		}
	}

	/**
	 * tests size for new 101 item list
	 * @param className
	 * @param list
	 */
	private static void test05_size_is101(String className, ListADT<String> list) {
		try{
			if(list.size() != 101) throw new Exception();
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("05");
			System.out.println("wrong size returned in: " + className);
			System.out.println("Should have returned 101, instead returned: " + list.size());
		}
	}

	/**
	 * tests remove by removing last 51 items
	 * @param className
	 * @param list
	 */
	private static void test06_remove_last_51(String className, ListADT<String> list) {
		try{
			for(int i = 0; i < 51; i++){
				list.remove(list.size() - 1);
			}
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("06");
			System.out.println("unable to remove with list.size()-1 in: " + className);
		}
	}

	/** 
	 * tests size
	 * @param className
	 * @param list
	 */
	private static void test07_size_is50(String className, ListADT<String> list) {
		try{
			if(list.size() != 50) throw new Exception();
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("07");
			System.out.println("wrong size returned in: " + className);
			System.out.println("Should have returned 50, instead returned: " + list.size());
		}
	}

	/**
	 * tests isEmpty() for false case
	 * @param className
	 * @param list
	 */
	private static void test08_isEmpty_false(String className, ListADT<String> list) {
		try{
			if(list.isEmpty()) throw new Exception();
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("08");
			System.out.println("isEmpty returned true incorrecly in: " + className);
		}
	}

	/**
	 * tests remove first 50
	 * @param className
	 * @param list
	 */
	private static void test09_remove_first_50(String className, ListADT<String> list) {
		try{
			for(int i = 0; i < 50; i++){
				list.remove(0);
			}
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("09");
			System.out.println("unable to remove items with remove(0): " + className);
		}
	}

	/**
	 * tests size() for empty list
	 * @param className
	 * @param list
	 */
	private static void test10_size_is0(String className, ListADT<String> list) {
		try{
			if(list.size() != 0) throw new Exception();
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("10");
			System.out.println("size() returned incorrect amount in: " + className);
			System.out.println("Should have returned 0, instead returned: " + list.size());
		}
	}

	/**
	 * tests isEmpty() for empty list
	 * @param className
	 * @param list
	 */
	private static void test11_isEmpty_true(String className, ListADT<String> list) {
		try{
			if(!list.isEmpty()) throw new Exception();
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("11");
			System.out.println("isEmpty incorrectly returned false in: " + className);
		}
	}

	private static void test12_add_abc(String className, ListADT<String> list) {
		try{
			list.add("a");
			list.add("b");
			list.add("c");
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("12");
			System.out.println("unable to add a, b, c in: " + className);
		}
	}

	private static void test13_check_get_abc(String className, ListADT<String> list) {
		String geta = "NULL";
		String getb = "NULL";
		String getc = "NULL";
		try{
			geta = list.get(0);
			getb = list.get(1);
			getc = list.get(2);
			if(!geta.equals("a")) throw new Exception();
			if(!getb.equals("b")) throw new Exception();
			if(!getc.equals("c")) throw new Exception();
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("13");
			System.out.println("get() returned wrong data in: " + className);
			System.out.println("get(0) should be 'a', instead get(0) is: " + geta);
			System.out.println("get(1) should be 'b', instead get(1) is: " + getb);
			System.out.println("get(2) should be 'c', instead get(2) is: " + getc);
		}
	}

	private static void test14_check_contains_abc(String className, ListADT<String> list) {
		try{
			if(!list.contains("a")) throw new Exception();
			if(!list.contains("b")) throw new Exception();
			if(!list.contains("c")) throw new Exception();
			if(list.contains("z")) throw new Exception();
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("14");
			System.out.println("contains() incorrecly returned false in: " + className);
			System.out.println("contains(a) should returned true, it returned: " + list.contains("a"));
			System.out.println("contains(b) should returned true, it returned: " + list.contains("b"));
			System.out.println("contains(c) should returned true, it returned: " + list.contains("c"));
			System.out.println("contains(z) should returned false, it returned: " + list.contains("z"));
		}
	}

	private static void test15_check_add_wpos(String className, ListADT<String> list) {
		try{
			list.add(3, "d");
			list.add(4, "e");
			list.add(5, "f");
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("15");
			System.out.println("add(pos,item) failed in: " + className);
		}
	}

	private static void test16_check_get_def(String className, ListADT<String> list) {
		String getd = "NULL";
		String gete = "NULL";
		String getf = "NULL";
		try{
			getd = list.get(3);
			gete = list.get(4);
			getf = list.get(5);
			if(!getd.equals("d")) throw new Exception();
			if(!gete.equals("e")) throw new Exception();
			if(!getf.equals("f")) throw new Exception();
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("16");
			System.out.println("get() returned wrong data in: " + className);
			System.out.println("get(3) should be 'd', instead get(3) is: " + getd);
			System.out.println("get(4) should be 'e', instead get(4) is: " + gete);
			System.out.println("get(5) should be 'f', instead get(5) is: " + getf);
		}
	}

	private static void test17_check_contains_def(String className, ListADT<String> list) {
		try{
			if(!list.contains("d")) throw new Exception();
			if(!list.contains("e")) throw new Exception();
			if(!list.contains("f")) throw new Exception();
			if(list.contains("z")) throw new Exception();
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("17");
			System.out.println("contains() incorrecly returned false in: " + className);
			System.out.println("contains(d) should returned true, it returned: " + list.contains("d"));
			System.out.println("contains(e) should returned true, it returned: " + list.contains("e"));
			System.out.println("contains(f) should returned true, it returned: " + list.contains("f"));
			System.out.println("contains(z) should returned false, it returned: " + list.contains("z"));
		}
	}

	private static void test18_check_remove_def(String className, ListADT<String> list) {
		String rmf = "NULL";
		String rme = "NULL";
		String rmd = "NULL";
		try{
			rmf = list.remove(5);
			rme = list.remove(4);
			rmd = list.remove(3);
			if(!rmf.equals("f")) throw new Exception();
			if(!rme.equals("e")) throw new Exception();
			if(!rmd.equals("d")) throw new Exception();
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("18");
			System.out.println("remove() removed wrong data in: " + className);
			System.out.println("remove(5) should have returned f, instead: " + rmf);
			System.out.println("remove(4) should have returned e, instead: " + rme);
			System.out.println("remove(3) should have returned d, instead: " + rmd);
		}
	}

	private static void test19_check_remove_abc(String className, ListADT<String> list) {
		String rma = "NULL";
		String rmb = "NULL";
		String rmc = "NULL";
		try{
			rma = list.remove(0);
			rmb = list.remove(0);
			rmc = list.remove(0);
			if(!rma.equals("a")) throw new Exception();
			if(!rmb.equals("b")) throw new Exception();
			if(!rmc.equals("c")) throw new Exception();
		}
		catch(Exception e){
			System.out.print(errmsg);
			System.out.println("19");
			System.out.println("remove() removed wrong data in: " + className);
			System.out.println("remove(0) should have returned f, instead: " + rma);
			System.out.println("remove(0) should have returned e, instead: " + rmb);
			System.out.println("remove(0) should have returned d, instead: " + rmc);
		}
	}
}
