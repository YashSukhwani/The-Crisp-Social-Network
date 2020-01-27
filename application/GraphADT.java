//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Course: cs400
// Semester: Fall 2019
//
// Team Members (A-Team: 70):
// 1. Ritvik Bhawnani, lec 002, and rbhawnani@wisc.edu
// 2. Fardeen Meeran, lec 001, and meeran@wisc.edu
// 3. Ryan Brown, lec 002, and rbrown38@wisc.edu
// 4. Yash Sukhwani, lec 001, and sukhwani@wisc.edu
// 5. Ayuj Prasad, lec 001, and prasad22@wisc.edu
//
//////////////////////////// PROJECT INFORMATION //////////////////////////////
//
// Project: Social Network
// Which team members were on same xteam together? NONE
//
// Other notes or comments to the grader:
// 1. Sign in page does not require any information. Simply select the SIGN IN 
// button.
// 2. Dan, Robert, and Julie are default friends.
// 3. User can specify names for their import and export files.
// 4. Add and Remove functionality have been implemented.
// 5. Button View Friends: Hides the profile section and displays current 
// friends alongside user
//////////////////// functions. User can
// now easily add and remove friends and see their changes in real-time.
// 6. Button Friend Network: Displays friends with current user as the central 
// user. User can easily
//////////////////// select the new
// central user from the friends list
// 7. Button Clear Network: Clears all friends (May NOT be all users)
//
///////////////////////////////////////////////////////////////////////////////

package application;

import java.util.List;
import java.util.Set;

/**
 * Filename: GraphADT.java Project: p4 Authors: A-Team 70
 *
 * This interface is the basis for a graph that will be created(DO NOT edit this
 * file)
 */
public interface GraphADT {

	/**
	 * Add new vertex to the graph.
	 *
	 * If vertex is null or already exists, method ends without adding a vertex or
	 * throwing an exception.
	 * 
	 * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in
	 * the graph
	 * 
	 * @param vertex the vertex to be added
	 */
	public void addVertex(String vertex);

	/**
	 * Remove a vertex and all associated edges from the graph.
	 * 
	 * If vertex is null or does not exist, method ends without removing a vertex,
	 * edges, or throwing an exception.
	 * 
	 * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in
	 * the graph
	 * 
	 * @param vertex the vertex to be removed
	 */
	public void removeVertex(String vertex);

	/**
	 * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and
	 * unweighted)
	 * 
	 * If either vertex does not exist, VERTEX IS ADDED and then edge is created. No
	 * exception is thrown.
	 *
	 * If the edge exists in the graph, no edge is added and no exception is thrown.
	 * 
	 * Valid argument conditions: 1. neither vertex is null 2. both vertices are in
	 * the graph 3. the edge is not in the graph
	 * 
	 * @param vertex1 the first vertex (src)
	 * @param vertex2 the second vertex (dst)
	 */
	public void addEdge(String vertex1, String vertex2);

	/**
	 * Remove the edge from vertex1 to vertex2 from this graph. (edge is directed
	 * and unweighted) If either vertex does not exist, or if an edge from vertex1
	 * to vertex2 does not exist, no edge is removed and no exception is thrown.
	 * 
	 * Valid argument conditions: 1. neither vertex is null 2. both vertices are in
	 * the graph 3. the edge from vertex1 to vertex2 is in the graph
	 * 
	 * @param vertex1 the first vertex
	 * @param vertex2 the second vertex
	 */
	public void removeEdge(String vertex1, String vertex2);

	/**
	 * Returns a Set that contains all the vertices
	 * 
	 * @return a Set<String> which contains all the vertices in the graph
	 */
	public List<String> getAllVertices();

	/**
	 * Get all the friends (adjacent-dependencies) of a vertex
	 * 
	 * For the example graph, A->[B, C], D->[A, B] getAdjacentVerticesOf(A) should
	 * return [B, C].
	 * 
	 * In terms of users, this list contains the immediate friends of A.
	 * 
	 * @param vertex the specified vertex
	 * @return an List<String> of all the adjacent vertices for specified vertex
	 */
	public List<String> getAdjacentVerticesOf(String vertex);

	/**
	 * Returns the number of edges in this graph.
	 * 
	 * @return number of edges in the graph.
	 */
	public int size();

	/**
	 * Returns the number of vertices in this graph.
	 * 
	 * @return number of vertices in graph.
	 */
	public int order();

}
