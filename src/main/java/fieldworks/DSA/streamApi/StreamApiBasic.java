package fieldworks.DSA.streamApi;

import java.util.Arrays;

public class StreamApiBasic {
	public static void main(String[] args) {
		//////////// LIST//////////////////

		// 1. some basic sorting related simple list
		// 1.1 => Integer
		// List<Integer> list = Arrays.asList(5, 2, 9, 1, 3);
		// //
		// Collections.sort(list);
		// //
		// Collections.sort(list, Collections.reverseOrder());
		// //
		// Collections.sort(list, new Comparator<Integer>() {
		// @Override
		// public int compare(Integer i1, Integer i2) {
		// return i1 - i2;
		// }
		// });
		//
		// java 8
		//
		// list.sort(Integer::compareTo); //🔹 Java 8 me sort (ascending)
		// // ya
		// list.sort(Comparator.naturalOrder());
		// list.sort(Comparator.reverseOrder()); //🔹 Java 8 me sort (descending)
		// list.sort((a, b) -> a - b); // 🔹 Lambda comparator (optional Java 8 style)
		//
		// using stream api
		//
		// List<Integer> sorted = list.stream().sorted().collect(Collectors.toList());
		//
		// List<Integer> sortedDesc =
		// list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		//
		// Custom comparator (same as i1 - i2)
		//
		// List<Integer> customSort = list.stream().sorted((i1, i2) -> i1 -
		// i2).collect(Collectors.toList());

		// // 1.2=> String
		// List<String> list = Arrays.asList("Banana", "Apple", "Mango", "Orange");
		// //
		// Collections.sort(list);
		// //
		// Collections.sort(list, Collections.reverseOrder());
		// //
		// Collections.sort(list, new Comparator<String>() {
		// @Override
		// public int compare(String s1, String s2) {
		// return s1.length() - s2.length();
		// }
		// });
		// System.out.println(list);
		//
		//
		// java 8
		//
		// list.sort(String::compareTo); // 🔹 Alphabetical order (A → Z)
		// list.sort(Comparator.reverseOrder()); // 🔹 Reverse alphabetical (Z → A)
		// list.sort(Comparator.comparingInt(String::length)); // 🔹 Length ke according
		// sort (short → long)
		// list.sort(Comparator.comparingInt(String::length).reversed()); // 🔹 Length
		// descending (long → short)
		// list.sort((s1, s2) -> s1.compareTo(s2)); // 🔹 Lambda comparator (optional
		// Java 8 style)
		//
		// using stream api
		//
		// Alphabetical order
		// List<String> sorted = list.stream().sorted().collect(Collectors.toList());
		// 🔄 Reverse alphabetical
		// List<String> sorted =
		// list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		//
		// Custom comparator (same as i1 - i2)
		//
		// List<String> customSort =list.stream().sorted((s1, s2) ->
		// s1.compareTo(s2)).collect(Collectors.toList());
		//
		// 📏 Length ke according sort
		// List<String> lengthSorted =
		// list.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
		//
		// 📏 Length descending
		// List<String> lengthDesc =
		// list.stream().sorted(Comparator.comparingInt(String::length).reversed()).collect(Collectors.toList());

		// 2. Even Numbers
		// List<Integer> list = new ArrayList<>();
		// list.add(2);
		// list.add(3);
		// list.add(4);
		// list.add(5);
		// list.add(6);
		//
		// List<Integer> evenList = list.stream().filter(num -> num%2 ==
		// 0).collect(Collectors.toList());
		// evenList.stream().forEach(num -> System.out.println(num));

		// 3. startWith 1
		// List<Integer> list = Arrays.asList(11, 22, 14, 55, 198, 67);
		//
		// using for loop
		//
		// for(Integer val : list) {
		// if(String.valueOf(val).startsWith("1")) {
		// System.out.println(val);
		// }
		// }
		//
		// using stream api
		//
		// List<Integer> startwith1 = list.stream().map(s -> String.valueOf(s)).filter(s
		// -> s.startsWith("1")).map(in->
		// Integer.parseInt(in)).collect(Collectors.toList());
		// startwith1.stream().forEach(s->System.out.println(s));

		// 4.find first element of list
		// List<Integer> list = Arrays.asList(11, 22, 14, 55, 198, 67);
		// list.stream().findAny().ifPresent(System.out::println);

		// 5. tottal number of elements
		// List<Integer> list = Arrays.asList(11, 22, 14, 55, 198, 67);
		// long num = list.stream().count();
		// System.out.println(num);

		/////////////// MAP
		/////////////// /////////////////////////////////////////////////////////////////////////////////////////////////////

		// Sorting Map Starting Here

		// 17. 🟢 CASE–1 : Simple Map (String → Integer)
		// Map<String, Integer> deptCount = new HashMap<>();
		// deptCount.put("IT", 5);
		// deptCount.put("HR", 2);
		// deptCount.put("Sales", 3);

		// ✅ 1️⃣ Loop based (Without Stream) – Key ke basis par
		// List<Map.Entry<String, Integer>> list = new
		// ArrayList<>(deptCount.entrySet());

		// Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
		// @Override
		// public int compare(Map.Entry<String, Integer> e1,
		// Map.Entry<String, Integer> e2) {
		// return e1.getKey().compareTo(e2.getKey());
		// }
		// });

		// //using java 8
		// list.sort(Map.Entry.comparingByKey());
		// list.sort(Map.Entry.comparingByKey(Collections.reverseOrder()));

		// ✅ 2️⃣ Loop based (Without Stream) – VALUE ke basis par
		// List<Map.Entry<String, Integer>> list =
		// new ArrayList<>(deptCount.entrySet());

		// Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
		// @Override
		// public int compare(Map.Entry<String, Integer> e1,
		// Map.Entry<String, Integer> e2) {
		// return e1.getValue().compareTo(e2.getValue());
		// }
		// });

		// //using java 8
		// list.sort(Map.Entry.comparingByValue());
		// list.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));

		// Map<String, Integer> sortedMap = new LinkedHashMap<>();
		// for (Map.Entry<String, Integer> entry : list) {
		// sortedMap.put(entry.getKey(), entry.getValue());
		// }

		// ✅ 1️⃣ Stream API se – KEY ke basis par sort
		// 🔹 Ascending (A → Z)
		// Map<String, Integer> sortedMap = new LinkedHashMap<>();
		// deptCount.entrySet().stream().sorted(Map.Entry.comparingByKey())
		// .forEach(entry -> sortedMap.put(entry.getKey(), entry.getValue()));
		// sortedMap.entrySet().stream().forEach(e-> System.out.println(e.getKey() + ":"
		// + e.getValue()));

		// // 🔹 Descending (Z → A)
		// Map<String, Integer> sortedMap = new LinkedHashMap<>();
		// deptCount.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByKey().reversed())
		// .forEach(entry -> sortedMap.put(entry.getKey(), entry.getValue()));
		// sortedMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() +
		// ":" + e.getValue()));

		// ✅ 2️⃣ Stream API se – VALUE ke basis par sort
		// 🔹 Ascending
		// Map<String, Integer> sortedMap = new LinkedHashMap<>();
		// deptCount.entrySet().stream().sorted(Map.Entry.comparingByValue())
		// .forEach(entry -> sortedMap.put(entry.getKey(), entry.getValue()));
		// sortedMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() +
		// ":" + e.getValue()));

		// 🔹 Descending
		// Map<String, Integer> sortedMap = new LinkedHashMap<>();
		// deptCount.entrySet().stream().sorted(Map.Entry.<String,
		// Integer>comparingByValue().reversed())
		// .forEach(entry -> sortedMap.put(entry.getKey(), entry.getValue()));
		// sortedMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() +
		// ":" + e.getValue()));

		// 🟢 CASE–2 : Object Map (Integer → Employee)
		// Example
		// Map<Integer, Employee> empMap = new HashMap<>();

		// // ✅ 1️⃣ Loop based – Employee NAME ke basis par
		// List<Map.Entry<Integer, Employee>> list = new ArrayList<>(empMap.entrySet());

		// Collections.sort(list, new Comparator<Map.Entry<Integer, Employee>>() {
		// @Override
		// public int compare(Map.Entry<Integer, Employee> e1, Map.Entry<Integer,
		// Employee> e2) {
		// return e1.getValue().getName().compareTo(e2.getValue().getName());
		// }
		// });

		// Map<Integer, Employee> sortedEmpMap = new LinkedHashMap<>();
		// for (Map.Entry<Integer, Employee> entry : list) {
		// sortedEmpMap.put(entry.getKey(), entry.getValue());
		// }

		// // ✅ 2️⃣ Loop based – Employee SALARY (Descending)
		// List<Map.Entry<Integer,Employee>> list = new ArrayList<>(empMap.entrySet());
		// Collections.sort(list, new Comparator<Map.Entry<Integer,Employee>>() {
		// @Override
		// public int compare(Map.Entry<Integer,Employee> e1,
		// Map.Entry<Integer,Employee> e2){
		// return Double.compare(e1.getValue().getSalary(), e2.getValue().getSalary());
		// }
		// });

		// ✅ 1️⃣ Stream API – Employee NAME ke basis par
		// // 🔹 Ascending
		// Map<Integer, Employee> sortedEmpMap = new LinkedHashMap<>();
		// empMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.comparing(Employee::getName)))
		// .forEach(entry -> sortedEmpMap.put(entry.getKey(), entry.getValue()));

		// // 🔹 Descending
		// empMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.comparing(Employee::getName).reversed()))
		// .forEach(e -> sortedEmpMap.put(e.getKey(), e.getValue()));

		/////////////////////////////////////////////////////////////////////////////////////////////////
		// PART-A : STRING BASED STREAM API (MOST ASKED)

		// 1. String = "Shailendra" → sabse zyada repeat hone wala character nikaalo
		// String name = "Shailendra";
		// Map<Character, Long> freqMap = name.chars().mapToObj(c -> (char) c)
		// .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
		//
		// Character mostRepeated =
		// freqMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
		// System.out.println(mostRepeated);

		// 2.Least repeated character nikaalo
		// Character leastRepeated =
		// freqMap.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
		// System.out.println(leastRepeated);

		// 3.First non-repeated character
		// String name = "swiss";
		// // LinkedHashMap<Character, Long> charMap =
		// name.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(c-> c,
		// LinkedHashMap::new, Collectors.counting()));
		// LinkedHashMap<Character, Long> charMap =
		// name.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(c-> c, ()->
		// new LinkedHashMap<>() , Collectors.counting()));
		// // charMap.entrySet().stream().forEach(entry->
		// System.out.println(entry.getKey()+":"+ entry.getValue()));
		// Character firstNonRepeated = charMap.entrySet().stream().filter(e->
		// e.getValue()==1).findFirst().get().getKey();
		// System.out.println(firstNonRepeated);
		//
		// using loop
		//
		// LinkedHashMap<Character, Integer> charMap = new LinkedHashMap<>();
		// for (int i = 0; i < name.length(); i++) {
		// charMap.put(name.charAt(i), charMap.getOrDefault(name.charAt(i), 0) + 1);
		// }
		// Character firstNonRepeated = null;
		// for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
		// if (entry.getValue() == 1) {
		// firstNonRepeated = entry.getKey();
		// break;
		// }
		// }
		// System.out.println(firstNonRepeated);

		// 4.Duplicate characters print karo
		// String name = "swiiss";
		// Map<Character, Long> charMap = name.chars().mapToObj(c-> (char)
		// c).collect(Collectors.groupingBy(c-> c, Collectors.counting()));
		// charMap.entrySet().stream().filter(e-> e.getValue()>1).forEach(entry ->
		// System.out.println(entry.getKey()+":" +entry.getValue()));

		// 5.Unique characters
		// String name = "swiiss";
		// name.chars().mapToObj(c-> (char) c).distinct().forEach(c->
		// System.out.println(c));

		// 6.Vowels me se sabse zyada repeat hone wala vowel
		// String vowels = "aeiouAEIOU";
		// String name = "swiissu";
		// Map<Character, Long> charVowelsMap =
		// name.chars().mapToObj(c->(char)c).filter(c-> vowels.indexOf(c) !=
		// -1).collect(Collectors.groupingBy(c->c, Collectors.counting()));
		// Character mostRepeatedVowel =
		// charVowelsMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
		// System.out.println(mostRepeatedVowel);

		// 7.Characters ko frequency ke basis par sort karo (descending)
		// String name = "Shailendra";
		// Map<Character, Long> charMap =
		// name.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(c-> c,
		// Collectors.counting()));
		// charMap.entrySet().stream().sorted(Map.Entry.<Character,
		// Long>comparingByValue().reversed()).forEach(e->
		// System.out.println(e.getKey()+":"+e.getValue()));

		// 8.Sentence se most repeated word
		// String sentence = "java stream api java stream java";
		// Map<String,Long> freqMap = Arrays.stream(sentence.split("
		// ")).collect(Collectors.groupingBy(w-> w, Collectors.counting()));
		// // freqMap.entrySet().stream().forEach(entry ->
		// System.out.println(entry.getKey()+":"+ entry.getValue()));
		// String mostRepeatedWord =
		// freqMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
		// System.out.println(mostRepeatedWord);

		// 9.First non-repeated word
		// String sentence = "java stream api java stream java";
		// String firstUniqueWord = Arrays.stream(sentence.split("
		// ")).collect(Collectors.groupingBy(c-> c, LinkedHashMap::new,
		// Collectors.counting())).entrySet().stream().filter(e->
		// e.getValue()==1).findFirst().get().getKey();
		// System.out.println(firstUniqueWord);

		/////////////////////////////////////////////////////////////////////////////////////

		// PART-B : ARRAY BASED STREAM API QUESTIONS

		// 1.fetch duplicate elements from Array
		// int[] arr = {1,2,3,4,2,3,5};
		// Map<Integer,Long> freqMap =
		// Arrays.stream(arr).boxed().collect(Collectors.groupingBy(n-> n,
		// Collectors.counting()));
		// 1st way
		// freqMap.entrySet().stream().filter(entry-> entry.getValue()>1).map(entry->
		// entry.getKey()).forEach(val -> System.out.println(val));
		// 2nd way
		// Map<Integer,Long> resultMap = freqMap.entrySet().stream().filter(entry->
		// entry.getValue()>1).collect(Collectors.toMap(entry-> entry.getKey(),
		// entry->entry.getValue()));
		// 3rd way
		// Map<Integer,Long> resultMap = freqMap.entrySet().stream().filter(entry->
		// entry.getValue()>1).collect(Collectors.toMap(Map.Entry::getKey,
		// Map.Entry::getValue));
		// 4th way => Order bhi maintain karna ho
		// Map<Integer,Long> resultMap = freqMap.entrySet().stream().filter(entry->
		// entry.getValue()>1).collect(Collectors.toMap(Map.Entry::getKey,
		// Map.Entry::getValue, (a,b)-> a, LinkedHashMap::new));
		// resultMap.entrySet().stream().forEach(entry ->
		// System.out.println(entry.getKey()));

		// 2.First non-repeated element
		// int[] arr = { 1, 2, 3, 4, 2, 3, 5, 1 };
		// Integer nonrepeatedelement = Arrays.stream(arr).boxed()
		// .collect(Collectors.groupingBy(n -> n, LinkedHashMap::new,
		// Collectors.counting())).entrySet().stream()
		// .filter(entry -> entry.getValue() == 1).findFirst().get().getKey();
		// System.out.println(nonrepeatedelement);

		// // 3.Second largest element
		// int[] arr = { 1, 2, 3, 4, 2, 3, 5, 1,6 };
		// // Integer secondLargest =
		// Arrays.stream(arr).distinct().boxed().sorted((a,b)->
		// Integer.compare(b,a)).skip(1).findFirst().get();
		// Integer secondLargest =
		// Arrays.stream(arr).distinct().boxed().sorted(Collections.reverseOrder()).skip(1).findFirst().get();
		// System.out.println(secondLargest);

		// 4.Top 3 largest elements
		// int[] arr = { 1, 2, 3, 4, 2, 3, 5, 1, 6 };
		// Arrays.stream(arr).distinct().boxed().sorted(Collections.reverseOrder()).limit(3).forEach(System.out::println);

		// 5.Sum of squares of even numbers
		// int[] arr = { 1, 2, 3, 4, 2, 3, 5, 1, 6 };
		// int sum = Arrays.stream(arr).filter(val -> val % 2 == 0).map(val -> val *
		// val).sum();
		// System.out.println(sum);

		// 6.Array ko frequency ke basis par sort karo
		// int[] arr = { 1, 2, 3, 4, 2, 3, 5, 1, 6, 2 };
		// Arrays.stream(arr).boxed().collect(Collectors.groupingBy(n -> n,
		// Collectors.counting())).entrySet().stream()
		// .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
		// .forEach(entry -> System.out.println(entry.getKey() + ":" +
		// entry.getValue()));

		// PART-C : ADVANCED / TRICK STREAM QUESTIONS

		// // 1.Infinite stream se first 10 even numbers
		// Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

		// // 2.Check karo all numbers even hain ya nahi
		// int[] arr = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 };
		// boolean allEven = Arrays.stream(arr).allMatch(n -> n % 2 == 0);
		// System.out.println(allEven);

		// // 3.Any number > 100 present hai ya nahi
		// int[] arr = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 101 };
		// boolean anyMatch = Arrays.stream(arr).anyMatch(n -> n > 100);
		// System.out.println(anyMatch);

		// // 4.Parallel stream ka use karke sum
		// int[] arr = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 };
		// int sum = Arrays.stream(arr).parallel().sum();
		// System.out.println(sum);

		// // 5.Imperative code → Stream API
		// int[] arr = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 };
		// // Imperative
		// int sum = 0;
		// for (int n : arr) {
		// if (n % 2 == 0) {
		// sum += n;
		// }
		// }

		// // Stream
		// int sum2 = Arrays.stream(arr)
		// .filter(n -> n % 2 == 0)
		// .sum();

	}
}