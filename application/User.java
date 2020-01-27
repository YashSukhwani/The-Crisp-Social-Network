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

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Random;

/**
 * GraphNodes hold string information and information about successors
 */
public class User {
	// instance fields
	private Image image;
	private String name;
	private String description;
	private ArrayList<User> friends = new ArrayList<User>();

	/**
	 * Initialize with the vertex value
	 *
	 * @param name user name
	 */
	public User(String name) {
		this.name = name;

		Random rand = new Random();
		this.image = getRandomImage(rand);
		this.description = getRandomDescription(rand);

	}

	/**
	 * Get Image
	 * 
	 * @return Image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Get User Name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get Profile Description
	 * 
	 * @return Description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get Friends
	 * 
	 * @return List of friends
	 */
	public ArrayList<User> getFriends() {
		return friends;
	}

	/**
	 * Updates to random image
	 * 
	 * @param rand - instance of random
	 * @return Image
	 */
	private Image getRandomImage(Random rand) {
		// Create list of picture strings
		String[] defaultDisplayPictures = new String[5];
		defaultDisplayPictures[0] = "default1.jpg";
		defaultDisplayPictures[1] = "default2.jpg";
		defaultDisplayPictures[2] = "default3.jpg";
		defaultDisplayPictures[3] = "default4.jpg";
		defaultDisplayPictures[4] = "default5.jpg";

		// Randomly Select
		int num = rand.nextInt(5);
		return new Image("file:" + defaultDisplayPictures[num]);
	}

	/**
	 * Gets a random Description
	 * 
	 * @param rand - instance of random
	 * @return random Description
	 */
	private String getRandomDescription(Random rand) {
		// Create List of Strings
		String[] defaultDescriptions = new String[5];
		defaultDescriptions[0] = "Today is a great day :)";
		defaultDescriptions[1] = "Never give up!";
		defaultDescriptions[2] = "What is your favorite TV show?";
		defaultDescriptions[3] = "Strive to be the best";
		defaultDescriptions[4] = "Excited for finals week!";

		// Randomly select string from list
		int num = rand.nextInt(5);
		return defaultDescriptions[num];
	}

}
