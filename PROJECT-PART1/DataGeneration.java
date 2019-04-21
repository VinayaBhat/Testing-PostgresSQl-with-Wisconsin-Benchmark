import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DataGeneration {
	/**
	 * Connects to the database and returns a Connection instance to be used to
	 * execute queries
	 **/
	public Connection ConnecttoDB() {
		final String url = "jdbc:postgresql://localhost/";
		final String user = "postgres";
		final String password = "1234";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to the PostgreSQL server successfully.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	/** Function to generate string4 **/
	public String generateString4(int count) {
		char[] str = new char[52];
		for (int k = 0; k < 52; k++) {
			str[k] = 'x';
		}
		String string4 = "";
		char str1 = 0;
		if (count == 1) {
			str1 = 'A';
			count++;
		} else if (count == 2) {
			str1 = 'H';
			count++;
		} else if (count == 3) {
			str1 = 'O';
			count++;
		} else if (count == 4) {
			str1 = 'V';
			count = 1;
		}
		for (int z = 0; z < 4; z++) {
			str[z] = str1;

			string4 = String.copyValueOf(str);
		}
		return string4;
	}

	/** Function to generate string 1 and 2 given unique1 and unique2 as input **/
	public String generatestring12(int unique1) {
		char[] str = new char[52];
		for (int k = 0; k < 52; k++) {
			str[k] = 'x';
		}
		String string1 = "";
		int rem = 0;
		for (int i = 0; i < 7; i++) {
			str[i] = 'A';
		}
		int i = 6;
		char temp[] = new char[7];
		while (unique1 > 0) {
			rem = unique1 % 26;
			temp[i] = (char) ('A' + rem);
			unique1 = unique1 / 26;
			i--;
		}
		for (i = i + 1; i <= 6; i++) {
			str[i] = temp[i];
		}
		string1 = String.copyValueOf(str);
		return string1;
	}

	public static void main(String[] args) {
		DataGeneration dg = new DataGeneration();
		Connection conn = dg.ConnecttoDB();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of tables:");
		int nooftables = sc.nextInt();
		int[] sizeoftuple = new int[nooftables];
		String[] nameoftable = new String[nooftables];

		for (int i = 0; i < nooftables; i++) {
			System.out.println("Enter name of the table:");
			nameoftable[i] = sc.next();
			System.out.println("Enter size of the table:");
			sizeoftuple[i] = sc.nextInt();
		}
		sc.close();

		for (int j = 0; j < nooftables; j++) {
			int maxtuples = sizeoftuple[j];

			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				stmt.execute("CREATE TABLE " + nameoftable[j]
						+ "(unique1 integer NOT NULL CONSTRAINT unique1_check check (unique1 between 0 and "
						+ (maxtuples - 1)
						+ "),unique2 integer NOT NULL PRIMARY KEY CONSTRAINT unique2_check check (unique2 between 0 and "
						+ (maxtuples - 1)
						+ "),unique3 integer NOT NULL CONSTRAINT unique3_check check (unique3 between 0 and "
						+ (maxtuples - 1)
						+ "),two integer NOT NULL CONSTRAINT two_check CHECK (two in (0,1)), four integer NOT NULL CONSTRAINT four_check CHECK (four in (0,1,2,3)), ten integer NOT NULL CONSTRAINT ten_check CHECK (ten between 0 and 9),twenty integer NOT NULL CONSTRAINT twenty_check CHECK (twenty between 0 and 19),onepercent integer NOT NULL CONSTRAINT onepercent_check CHECK (onepercent between 0 and 99),tenpercent integer NOT NULL CONSTRAINT tenpercent_check CHECK (tenpercent between 0 and 9),twentypercent integer NOT NULL CONSTRAINT twentypercent_check CHECK(twentypercent in (0,1,2,3,4)),fiftypercent integer NOT NULL CONSTRAINT fiftypercent_check CHECK(fiftypercent in (0,1)), evenOnePercent integer NOT NULL CONSTRAINT evenOnePercent_check CHECK(evenOnePercent between 0 and 198 and evenOnePercent%2=0),oddOnePercent integer NOT NULL CONSTRAINT oddOnePercent_check CHECK(oddOnePercent between 0 and 199 and oddOnePercent%2!=0),stringu1 char(52) NOT NULL, stringu2 char(52) NOT NULL, string4 char(52) NOT NULL,CONSTRAINT "
						+ nameoftable[j] + "_unique1 UNIQUE (unique1),CONSTRAINT " + nameoftable[j]
						+ "_unique2 UNIQUE (unique2),CONSTRAINT " + nameoftable[j]
						+ "_unique3 UNIQUE (unique3),CONSTRAINT " + nameoftable[j]
						+ "_stringu1 UNIQUE (stringu1),CONSTRAINT " + nameoftable[j] + "_stringu2 UNIQUE (stringu2))");
			} catch (Exception e) {
				e.printStackTrace();
			}

			int[] unique1 = new int[maxtuples];
			int[] unique2 = new int[maxtuples];
			int[] two = new int[maxtuples];
			int[] four = new int[maxtuples];
			int[] ten = new int[maxtuples];
			int[] twenty = new int[maxtuples];
			int[] onepercent = new int[maxtuples];
			int[] tenpercent = new int[maxtuples];
			int[] twentypercent = new int[maxtuples];
			int[] fiftypercent = new int[maxtuples];
			int[] unique3 = new int[maxtuples];
			int[] evenOnePercent = new int[maxtuples];
			int[] oddOnePercent = new int[maxtuples];
			String[] string1 = new String[maxtuples];
			String[] string2 = new String[maxtuples];
			String[] string4 = new String[maxtuples];
			int str4count = 1;
			/** Generating unique1 **/
			ArrayList<Integer> templist = new ArrayList<>();
			for (int k = 0; k < maxtuples; k++) {
				templist.add(k);
			}
			Collections.shuffle(templist);
			for (int i = 0; i < maxtuples; i++) {
				/** Unique1 **/
				unique1[i] = templist.get(i);
				/** Unique2 **/
				unique2[i] = i;
				/** two **/
				two[i] = unique1[i] % 2;
				/** four **/
				four[i] = unique1[i] % 4;
				/** ten **/
				ten[i] = unique1[i] % 10;
				/** twenty **/
				twenty[i] = unique1[i] % 20;
				/** onepercent **/
				onepercent[i] = unique1[i] % 100;
				/** tenpercent **/
				tenpercent[i] = unique1[i] % 10;
				/** twentypercent **/
				twentypercent[i] = unique1[i] % 5;
				/** fiftypercent **/
				fiftypercent[i] = unique1[i] % 2;
				/** unique3 */
				unique3[i] = unique1[i];
				/** evenonepercent **/
				evenOnePercent[i] = onepercent[i] * 2;
				/** oddonepercent **/
				oddOnePercent[i] = (onepercent[i] * 2) + 1;
				/** String1 **/
				string1[i] = dg.generatestring12(unique1[i]);
				/** String2 **/
				string2[i] = dg.generatestring12(unique2[i]);
				/** String4 **/
				string4[i] = dg.generateString4(str4count);
				if (str4count == 4) {
					str4count = 1;
				} else {
					str4count++;
				}
				try {
					stmt.addBatch("Insert into " + nameoftable[j] + " values(" + unique1[i] + "," + unique2[i] + ","
							+ unique3[i] + "," + two[i] + "," + four[i] + "," + ten[i] + "," + twenty[i] + ","
							+ onepercent[i] + "," + tenpercent[i] + "," + twentypercent[i] + "," + fiftypercent[i] + ","
							+ evenOnePercent[i] + "," + oddOnePercent[i] + ",'" + string1[i] + "','" + string2[i]
							+ "','" + string4[i] + "')");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				stmt.executeBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
