package undirected;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphSearch {
	public static boolean dfs(int start, int goal, Graph G) {
		// Initialize: stack, visited HashSet and parent HashMap
		Stack<Integer> toExplore = new Stack<>();
		Set<Integer> visited = new HashSet<>();
		Map<Integer, Integer> parent = new HashMap<>();
		
		// Push S onto the stack and add to visited
		toExplore.push(start);
		visited.add(start);
		
		// while stack is not empty
		while(!toExplore.isEmpty()) {
			// pop node curr from top of stack
			int curr = toExplore.pop();
			System.out.println("Visited vertex: " + curr);
			// if curr == G return parent map
			if(curr == goal) {
				return true;
			}
			
			// for each of curr’s neighbors, n, not in visited set
			List<Integer> neighbors = (List<Integer>) G.adj(curr);
			ListIterator<Integer> it = neighbors.listIterator(neighbors.size());
			while(it.hasPrevious()) {
				int next = it.previous();
				if(!visited.contains(next)) {
					// add n to visited set
					visited.add(next);
					
					// add curr as n’s parent in parent map
					parent.put(next, curr);
					
					// push n onto the stack
					toExplore.push(next);
				}
			}
		}
		
		return false;
	}
	
	public static boolean bfs(int start, int goal, Graph G) {
		// Initialize: queue, visited HashSet and parent HashMap
		Queue<Integer> toExplore = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		Map<Integer, Integer> parent = new HashMap<>();
		
		// Enqueue S onto the queue and add to visited
		toExplore.add(start);
		visited.add(start);
		
		// while queue is not empty
		while(!toExplore.isEmpty()) {
			// dequeue node curr from front of queue
			int curr = toExplore.remove();
			System.out.println("Visited vertex: " + curr);
			// if curr == G return parent map
			if(curr == goal) {
				return true;
			}
			
			// for each of curr’s neighbors, n, not in visited set
			List<Integer> neighbors = (List<Integer>) G.adj(curr);
			ListIterator<Integer> it = neighbors.listIterator(neighbors.size());
			while(it.hasPrevious()) {
				int next = it.previous();
				if(!visited.contains(next)) {
					// add n to visited set
					visited.add(next);
					
					// add curr as n’s parent in parent map
					parent.put(next, curr);
					
					// enqueue n onto the queue
					toExplore.add(next);
				}
			}
		}
		return false;
	}
}
