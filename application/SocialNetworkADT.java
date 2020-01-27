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

/**
 * Filename: SocialNetworkADT.java Project: p4 Authors: A-Team 70
 *
 * This interface is the basis for a SocialNetwork instance that will be
 * created(DO NOT edit this file)
 */
public interface SocialNetworkADT {

	/**
	 * Get friends of a user
	 *
	 * @param user the user whose friends we need to retrieve
	 * @return the SocialNetwork
	 */
	public List<String> displayNetwork(String user);

	/**
	 * Set the central user of the network
	 *
	 * @param centralUser the central user who we need to set
	 * @return true if the central user was set
	 */
	public boolean setCentralUser(String central);

	/**
	 * Get the central user of the network
	 *
	 * @return string central user's name
	 */
	public String getCentralUser();

	/**
	 * Add user to the netwrok
	 *
	 * @param user the user to be added
	 * @return true if user was added to the network
	 */
	public boolean addUser(String user);

	/**
	 * Remove user from the network
	 *
	 * @param user the user to be removed
	 * @return true if user was removed from the network
	 */
	public boolean removeUser(String user);

	/**
	 * Add friend for the central user
	 *
	 * @param friend the user to be added
	 * @return true if friend was added
	 */
	public boolean addFriend(String friend);

	/**
	 * Remove friends from the central user
	 *
	 * @param friend the friend that needs to be removed
	 * @return true if the friend was removed
	 */
	public boolean removeFriend(String friend);

	/**
	 * Add Friendship between any two users
	 *
	 * @param friend1 the first user
	 * @param friend2 the second user
	 * @return true if the friedship was added to the network
	 */
	public boolean addFriend(String friend1, String friend2);

	/**
	 * Removes the friendship between two users
	 *
	 * @param friend1 the first friend
	 * @param friend2 the second friend
	 * @return true if the friendship was removed
	 */
	public boolean removeFriend(String friend, String friend2);

	/**
	 * Reset SocialNetwork to remove all users
	 */
	public void removeAllUsers();

	/**
	 * Find mutual friends between two users
	 *
	 * @param user1 the first user
	 * @param user2 the second user
	 * @return list of mutual friends between two users
	 */
	public List<String> getMutualFriends(String user1, String user2);

	/**
	 * Get all users from the SocialNetwork
	 *
	 * @return list of all users in the Network
	 */
	public List<String> getAllUsers();

	/**
	 * Special remove method to use during importing.
	 * @param user
	 * @return
	 */
	boolean removeUserDuringImport(String user);

}
