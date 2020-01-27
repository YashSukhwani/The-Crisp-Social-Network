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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This class creates a social network object that maintains users, central
 * users, files, and more.
 * 
 * @author Team 70
 *
 */
public class SocialNetwork implements SocialNetworkADT {
	// Fields
	private Graph graph;
	private String centralUser;
	private FileManager fileManager;
	private PrintWriter logFileWriter;

	/**
	 * Initialize values
	 */
	public SocialNetwork() {
		this.centralUser = null;
		this.graph = new Graph();
		this.fileManager = new FileManager(this);
		this.logFileWriter = createLogFileWriter();
	}

	@Override
	/**
	 * Get friends of a user
	 * 
	 * @param user
	 */
	public List<String> displayNetwork(String user) {
		return graph.getAdjacentVerticesOf(user);
	}

	@Override
	/**
	 * Set central user
	 * 
	 * @param centralUser
	 * @return true if works
	 */
	public boolean setCentralUser(String centralUser) {
		this.centralUser = centralUser;
		fileManager.updateLog("s " + centralUser, logFileWriter);
		return true;
	}

	@Override
	/**
	 * Get the central user
	 * 
	 * @return string central user
	 */
	public String getCentralUser() {
		if (centralUser == null) {
			List<String> allUsers = graph.getAllVertices();

			if (allUsers.size() > 0)
				centralUser = allUsers.get(0);

			return centralUser;
		}

		return centralUser;
	}

	@Override
	/**
	 * Add user
	 * 
	 * @param user
	 * @return true if added
	 */
	public boolean addUser(String user) {
		// Check is user exists
		if (!graph.getAllVertices().contains(user) && isValidUser(user)) {
			graph.addVertex(user);
			fileManager.updateLog("a " + user, logFileWriter);
			return true;
		} else {
			return false;
		}

	}

	@Override
	/**
	 * Remove user
	 * 
	 * @param user
	 * @return true if removed
	 */
	public boolean removeUser(String user) {
		// Check if exists
		if (getAllUsers().contains(user)) {
			graph.removeVertex(user);
			fileManager.updateLog("r " + user, logFileWriter);
		} else {
			return false;
		}
		return true;
	}
	
	@Override
	/**
	 * Remove user. This method is used during importing.
	 * 
	 * @param user
	 * @return true if removed
	 */
	public boolean removeUserDuringImport(String user) {
		// Check if exists
		if (getAllUsers().contains(user)) {
			if(user.equals(centralUser)) {
				Alert exportAlert = new Alert(AlertType.ERROR, 
						"Cannot remove central user when importing");
				exportAlert.show();
				return false;
			} else {
				graph.removeVertex(user);
				fileManager.updateLog("r " + user, logFileWriter);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	/**
	 * Add friend
	 * 
	 * @param friend
	 * @return true if added
	 */
	public boolean addFriend(String friend) {
		// Check if exists
		if (!centralUser.equals(friend) && isValidUser(friend)) {
			graph.addEdge(centralUser, friend);
			fileManager.updateLog("a " + centralUser + " " + friend, logFileWriter);
			return true;
		} else {
			return false;
		}

	}

	@Override
	/**
	 * Remove friends
	 * 
	 * @param friend
	 * @return true if removed
	 */
	public boolean removeFriend(String friend) {
		// True if added
		if (!centralUser.equals(friend)) {
			graph.removeEdge(centralUser, friend);
			fileManager.updateLog("r " + centralUser + " " + friend, logFileWriter);
			return true;
		} else {
			return false;
		}

	}

	@Override
	/**
	 * Add Friend between any two users
	 * 
	 * @param friend1
	 * @param friend2
	 * @return true if added
	 */
	public boolean addFriend(String friend1, String friend2) {
		// Check if users exist and should be added
		if (!friend1.equals(friend2) && isValidUser(friend1) && isValidUser(friend2)) {
			graph.addEdge(friend1, friend2);
			fileManager.updateLog("a " + friend1 + " " + friend2, logFileWriter);
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * Check if friends exist
	 * 
	 * @param friend1
	 * @param friend2
	 * @return true if removed
	 */
	public boolean removeFriend(String friend1, String friend2) {
		// Check if friends are different
		if (!friend1.equals(friend2)) {
			graph.removeEdge(friend1, friend2);
			fileManager.updateLog("r " + friend1 + " " + friend2, logFileWriter);
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * Reset Graph to remove users
	 */
	public void removeAllUsers() {
		graph = null;
	}

	@Override
	/**
	 * Find mutual friends
	 * 
	 * @param user1
	 * @param user2
	 * @return list of mutual friends between two users
	 */
	public List<String> getMutualFriends(String user1, String user2) {
		// Create two friend list of each user and merge them
		List<String> user1Friends = graph.getAdjacentVerticesOf(user1);
		List<String> user2Friends = graph.getAdjacentVerticesOf(user2);
		List<String> mutualFriends = new ArrayList<String>();

		// Loop through each friend and add if same as another users friend
		for (String friend : user1Friends) {
			if (user2Friends.contains(friend)) {
				mutualFriends.add(friend);
			}
		}
		return mutualFriends;
	}

	@Override
	/**
	 * Get all users
	 * 
	 * @return list of all users
	 */
	public List<String> getAllUsers() {
		return graph.getAllVertices();
	}

	/**
	 * Get specific user
	 * 
	 * @param user String
	 * @return User object
	 */
	public User getUser(String user) {
		return graph.getNode(user);
	}

	/**
	 * Import a file and read it
	 * 
	 * @param fileName
	 * @return true if added successfully
	 */
	public boolean importFile(String fileName) {
		// Keeps tally of how many line errors there were and reports that info
		// to the user
		int count = (int) fileManager.importFile(fileName, true).get(1);
		if (count > 0) {
			Alert addUserAlert = new Alert(AlertType.ERROR,
					"Encountered " + count + " Line(s) With Errors When Reading File");
			addUserAlert.show();
		}
		// return true if successfully imported
		return (boolean) fileManager.importFile(fileName, false).get(0);
	}

	/**
	 * Export file
	 * 
	 * @param fileName
	 * @return true if successful
	 */
	public boolean exportFile(String fileName) {
		// Close unused FileWriter and export to file name
		logFileWriter.close();
		return fileManager.exportFile(fileName);
	}

	/**
	 * Find Shortest path
	 * 
	 * @param user1
	 * @param user2
	 * @return List of shortest path
	 */
	public LinkedList<String> getShortestPath(String user1, String user2) {
		return graph.shortestPath(graph.getNode(user1), graph.getNode(user2));
	}

	/**
	 * Get Groups
	 * 
	 * @return Number of Groups that exist in the graph
	 */
	public int getGroups() {
		return graph.getConnectedComponents();
	}

	/**
	 * Helper method to check if a new user is valid
	 * 
	 * @param name name of user
	 * @return true if valid; false otherwise
	 */
	private boolean isValidUser(String name) {
		// Loop through all characters in name and check that each one is legal
		for (int i = 0; i < name.length(); i++) {
			Character letter = name.charAt(i);
			if (!Character.isLetter(letter) && !Character.isDigit(letter) && !letter.equals('\'')
					&& !letter.equals('_')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Create Create File Writer
	 * 
	 * @return the PrintWriter
	 */
	private PrintWriter createLogFileWriter() {
		File file = new File("log.txt");
		PrintWriter writer = null;

		try {
			// Try to create file, and return if successful
			// Otherwise print errors
			file.createNewFile();
			writer = new PrintWriter(file);
			writer.print("");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer;
	}

}
