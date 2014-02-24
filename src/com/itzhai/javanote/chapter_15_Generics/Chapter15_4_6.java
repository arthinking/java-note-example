package com.itzhai.javanote.chapter_15_Generics;

import static temp.net.mindview.util.Sets.complement;
import static temp.net.mindview.util.Sets.difference;
import static temp.net.mindview.util.Sets.intersection;
import static temp.net.mindview.util.Sets.union;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import com.itzhai.javanote.util.Sets;
/**
 * 15 泛型  
 *   4 泛型方法
 *     6 一个Set实用工具
 * 
 * 
 */
enum Watercolors {
    ZINC, LEMON_YELLOW, MEDIUM_YELLOW, DEEP_YELLOW, ORANGE,
    BRILLIANT_RED, CRIMSON, MAGENTA, ROSE_MADDER, VIOLET,
    CERULEAN_BLUE_HUE, PHTHALO_BLUE, ULTRAMARINE,
    COBALT_BLUE_HUE, PERMANENT_GREEN, VIRIDIAN_HUE,
    SAP_GREEN, YELLOW_OCHRE, BURNT_SIENNA, RAW_UMBER,
    BURNT_UMBER, PAYNES_GRAY, IVORY_BLACK
}

class WatercolorSets {
    public static void main(String[] args) {
        Set<Watercolors> set1 =
                EnumSet.range(Watercolors.BRILLIANT_RED, Watercolors.VIRIDIAN_HUE);
        Set<Watercolors> set2 =
                EnumSet.range(Watercolors.CERULEAN_BLUE_HUE, Watercolors.BURNT_UMBER);
        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2);
        System.out.println("union(set1, set2): " + union(set1, set2));
        Set<Watercolors> subset = intersection(set1, set2);
        System.out.println("intersection(set1, set2): " + subset);
        System.out.println("difference(set1, subset): " +
                difference(set1, subset));    
        System.out.println("difference(set2, subset): " +
                difference(set2, subset));
        System.out.println("complement(set1, set2): " +
                complement(set1, set2));
    } 
} /* Output: (Sample)
  set1: [BRILLIANT_RED, CRIMSON, MAGENTA, ROSE_MADDER, VIOLET, CERULEAN_BLUE_HUE, PHTHALO_BLUE, ULTRAMARINE, COBALT_BLUE_HUE, PERMANENT_GREEN, VIRIDIAN_HUE]
  set2: [CERULEAN_BLUE_HUE, PHTHALO_BLUE, ULTRAMARINE, COBALT_BLUE_HUE, PERMANENT_GREEN, VIRIDIAN_HUE, SAP_GREEN, YELLOW_OCHRE, BURNT_SIENNA, RAW_UMBER, BURNT_UMBER]
  union(set1, set2): [SAP_GREEN, ROSE_MADDER, YELLOW_OCHRE, PERMANENT_GREEN, BURNT_UMBER, COBALT_BLUE_HUE, VIOLET, BRILLIANT_RED, RAW_UMBER, ULTRAMARINE, BURNT_SIENNA, CRIMSON, CERULEAN_BLUE_HUE, PHTHALO_BLUE, MAGENTA, VIRIDIAN_HUE]
  intersection(set1, set2): [ULTRAMARINE, PERMANENT_GREEN, COBALT_BLUE_HUE, PHTHALO_BLUE, CERULEAN_BLUE_HUE, VIRIDIAN_HUE]
  difference(set1, subset): [ROSE_MADDER, CRIMSON, VIOLET, MAGENTA, BRILLIANT_RED]
  difference(set2, subset): [RAW_UMBER, SAP_GREEN, YELLOW_OCHRE, BURNT_SIENNA, BURNT_UMBER]
  complement(set1, set2): [SAP_GREEN, ROSE_MADDER, YELLOW_OCHRE, BURNT_UMBER, VIOLET, BRILLIANT_RED, RAW_UMBER, BURNT_SIENNA, CRIMSON, MAGENTA]
  *///:~

// 下面的示例使用Sets.difference() 打印出 java.util包中各种Collection类与Map类之间的方法差异：
class ContainerMethodDifferences {
    static Set<String> methodSet(Class<?> type) {
      Set<String> result = new TreeSet<String>();
      for(Method m : type.getMethods())
        result.add(m.getName());
      return result;
    }
    static void interfaces(Class<?> type) {
      System.out.print("Interfaces in " +
        type.getSimpleName() + ": ");
      List<String> result = new ArrayList<String>();
      for(Class<?> c : type.getInterfaces())
        result.add(c.getSimpleName());
      System.out.println(result);
    }
    static Set<String> object = methodSet(Object.class);
    static { object.add("clone"); }
    static void
    difference(Class<?> superset, Class<?> subset) {
      System.out.print(superset.getSimpleName() +
        " extends " + subset.getSimpleName() + ", adds: ");
      Set<String> comp = Sets.difference(
        methodSet(superset), methodSet(subset));
      comp.removeAll(object); // Don't show 'Object' methods
      System.out.println(comp);
      interfaces(superset);
    }
    public static void test(String[] args) {
      System.out.println("Collection: " +
        methodSet(Collection.class));
      interfaces(Collection.class);
      difference(Set.class, Collection.class);
      difference(HashSet.class, Set.class);
      difference(LinkedHashSet.class, HashSet.class);
      difference(TreeSet.class, Set.class);
      difference(List.class, Collection.class);
      difference(ArrayList.class, List.class);
      difference(LinkedList.class, List.class);
      difference(Queue.class, Collection.class);
      difference(PriorityQueue.class, Queue.class);
      System.out.println("Map: " + methodSet(Map.class));
      difference(HashMap.class, Map.class);
      difference(LinkedHashMap.class, HashMap.class);
      difference(SortedMap.class, Map.class);
      difference(TreeMap.class, Map.class);
    }
}

public class Chapter15_4_6 {
	public static void main(String[] args){
	    ContainerMethodDifferences.test(args);
	}
}