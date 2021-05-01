package Final;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TestInputOutputMovie {

	/**
	 * Datatype for Movies. Each movie has a name and a list of Actors
	 */
	class Movie implements Serializable {
		/**
		 * Name of the Movie -- assumed to be unique
		 */
		private String name;
		private ArrayList<String> Actors;
		private double rating;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public ArrayList<String> getActors() {
			return Actors;
		}

		public void setActors(ArrayList<String> myActors) {
			this.Actors = myActors;
		}

		public Movie(String title) {
			name = title;
			Actors = new ArrayList<String>();
		}

		/**
		 * Add an Actor to our list of cast members
		 *
		 * @param person Actor that appeared in this Movie
		 */
		public void addActor(String person) {
			Actors.add(person);
		}

		/**
		 * The name of the Movie is assumed to be unique, so it is used as a HashCode
		 *
		 * @see java.lang.Object#hashCode()
		 */
		public int hashCode() {
			return name.hashCode();
		}

		public double getRating() {
			return rating;
		}

		public void setRating(double rating) {
			this.rating = rating;
		}

		// toString() method
		@Override
		public String toString() {
			return "Movie's Name: " + this.name + "\nActors: " + this.Actors.toString() + "\nRating: " + this.rating
					+ "\n";
		}

	}

	public static void main(String[] args) {
		TestInputOutputMovie testm = new TestInputOutputMovie();
		Movie movie1 = testm.new Movie("Godzilla vs. Kong");
		movie1.setActors(
				new ArrayList<String>(Arrays.asList("Alexander Skarsgård", "Millie Bobby Brown", "Rebecca Hall")));
		movie1.setRating(6.6);

		Movie movie2 = testm.new Movie("Zack Snyder's Justice League: Justice Is Gray");
		movie2.setActors(new ArrayList<String>(Arrays.asList("Henry Cavill", "Ben Affleck", "Gal Gadot")));
		movie2.setRating(8.2);

		Movie movie3 = testm.new Movie("Raya and the Last Dragon");
		movie3.setActors(new ArrayList<String>(Arrays.asList(" Kelly Marie Tran", "Awkwafina", "Gemma Chan")));
		movie3.setRating(7.8);

		try (FileOutputStream file = new FileOutputStream("Exercise17_06.dat ");
				ObjectOutputStream output = new ObjectOutputStream(file);){			

			output.writeObject(movie1);
			output.writeObject(movie2);
			output.writeObject(movie3);

			FileInputStream fileStream = new FileInputStream("Exercise17_06.dat ");

			ObjectInputStream input = new ObjectInputStream(fileStream);

			Movie readMovie1 = input.readObject();
			Movie readMovie2 = input.readObject();
			Movie readMovie3 = input.readObject();

			System.out.println(readMovie1.toString());
			System.out.println(readMovie2.toString());
			System.out.println(readMovie3.toString());
			
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
}
