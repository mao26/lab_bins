import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Runs a number of algorithms that try to fit files onto disks.
 */
public class Bins {
	public static final String DATA_FILE = "example.txt";
	private static int total = 0;
	private static PriorityQueue<Disk> pq;
	private static boolean totalTrue = true;
	private static List<Integer> data;

	/**
	 * Reads list of integer data from the given input.
	 *
	 * @param input
	 *            tied to an input source that contains space separated numbers
	 * @return list of the numbers in the order they were read
	 */
	public List<Integer> readData(Scanner input) {
		List<Integer> results = new ArrayList<Integer>();
		while (input.hasNext()) {
			results.add(input.nextInt());
		}
		return results;
	}

	/**
	 * The main program.
	 */
	protected PriorityQueue<Disk> creationDisks() {
		return null;
	}

	protected void createDiskBins() {

		pq.add(new Disk(0));
		int diskId = 1;

		for (Integer size : data) {
			Disk d = pq.peek();
			if (d.freeSpace() > size) {
				pq.poll();
				d.add(size);
				pq.add(d);
			} else {
				Disk d2 = new Disk(diskId);
				diskId++;
				d2.add(size);
				pq.add(d2);
			}
			total += size;
		}
		if (totalTrue) {
			System.out.println("total size = " + total / 1000000.0 + "GB");
			System.out.println();
		}
	}

	public static void main(String args[]) {
		Bins b = new Bins();
		b.go();
	}

	private void printWorstFit() {

		System.out.println("worst-fit method");
		System.out.println("number of pq used: " + pq.size());
		printQueue();
	}

	private void printQueue() {
		// TODO Auto-generated method stub
		while (!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
		System.out.println();
	}

	private void printReverseFit() {
		Collections.sort(data, Collections.reverseOrder());
		createDiskBins();
		System.out.println();
		System.out.println("worst-fit decreasing method");
		System.out.println("number of pq used: " + pq.size());
		printQueue();
	}

	private void go() {
		initVars();
		createDiskBins();
		totalTrue = false;
		printWorstFit();
		printReverseFit();

	}

	private void initVars() {
		Scanner input = new Scanner(Bins.class.getClassLoader().getResourceAsStream(DATA_FILE));
		data = readData(input);
		pq = new PriorityQueue<Disk>();
	}
}
