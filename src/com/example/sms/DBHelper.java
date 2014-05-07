package com.example.sms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	// Logcat tag
	// private static final String LOG = "DatabaseHelper";

	// Database Version
	private static final int DATABASE_VERSION = 2;

	// Database Name
	private static final String DATABASE_NAME = "data";

	// Database
	SQLiteDatabase database = null;

	// Table Names
	private static final String COURSE_TABLE = "course_table";
	private static final String CLASS_TABLE = "class_table";
	private static final String TEACHER_TABLE = "teacher_table";
	private static final String TEACHER_COURSE = "teacher_course";
	private static final String STUDENT_TABLE = "student_table";
	private static final String STUDENT_COURSE = "student_course";
	private static final String PERFORMANCE_TABLE = "performance_table";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create table commands

		db.execSQL("Create table if not exists course_table ( "
				+ "Course_id text primary key not null,"
				+ "Course_name text not null);");

		db.execSQL("Create table if not exists class_table ("
				+ "Class_id text primary key not null,"
				+ "End_date text not null);");

		db.execSQL("Create table if not exists teacher_table ("
				+ "Teacher_id text primary key not null,"
				+ "Teacher_name text not null," + "Password text not null);");

		db.execSQL("Create table if not exists teacher_course ("
				+ "Teacher_id text not null,"
				+ "Course_id text not null,"
				+ "Primary key (teacher_id, course_id),"
				+ "Foreign key (teacher_id) references teacher_table (teacher_id),"
				+ "Foreign key (course_id) references course_table (course_id));");

		db.execSQL("Create table if not exists student_table ("
				+ "Student_id text primary key not null,"
				+ "Student_name text not null," 
				+ "Class_id text not null,"
				+ "Sports integer not null,"
				+ "Foreign key (class_id) references class_table (class_id));");

		db.execSQL("Create table if not exists student_course ("
				+ "student_id text not null,"
				+ "course_id text not null,"
				+ "Primary key (student_id, course_id),"
				+ "Foreign key (student_id) references student_table (student_id),"
				+ "Foreign key (course_id) references course_table (course_id));");

		db.execSQL("Create table if not exists performance_table ("
				+ "Student_id text not null,"
				+ "Course_id text not null,"
				+ "Mid_sem integer not null,"
				+ "End_sem integer not null,"
				+ "Project integer not null,"
				+ "Attendance integer not null,"
				+ "Assignment integer not null,"
				+ "Behavior text,"
				+ "Primary key (student_id, course_id),"
				+ "Foreign key(student_id) references student_table (student_id),"
				+ "Foreign key(course_id) references course_table (course_id));");

		// Course_table

		db.execSQL("insert into course_table VALUES ('401', 'Computational Mathematics');");
		db.execSQL("insert into course_table VALUES ('402', 'Data Structure');");
		db.execSQL("insert into course_table VALUES ('403', 'Software Engineering');");
		db.execSQL("insert into course_table VALUES ('404', 'Database Management Systems');");
		db.execSQL("insert into course_table VALUES ('405', 'Object Oriented System');");

		// Teacher_table

		db.execSQL("insert into teacher_table VALUES ('rlal', 'Dr Ramjee Lal', 'password');");
		db.execSQL("insert into teacher_table VALUES ('bssanjeev', 'Dr B.S. Sanjeev', 'password');");
		db.execSQL("insert into teacher_table VALUES ('pchakraborty', 'Dr Pavan Chakraborty', 'password');");
		db.execSQL("insert into teacher_table VALUES ('opvyas', 'Dr O.P. Vyas', 'password');");
		db.execSQL("insert into teacher_table VALUES ('ssanyal', 'Dr Sudip Sanyal', 'password');");

		// Teacher_course

		db.execSQL("insert into teacher_course VALUES ('rlal', '401');");
		db.execSQL("insert into teacher_course VALUES ('bssanjeev', '402');");
		db.execSQL("insert into teacher_course VALUES ('pchakraborty', '403');");
		db.execSQL("insert into teacher_course VALUES ('opvyas', '404');");
		db.execSQL("insert into teacher_course VALUES ('ssanyal', '405');");

		// Student_table

		db.execSQL("insert into student_table VALUES ('iit2012092', 'Soumyarka Mondal', 'it1', 1);");
		db.execSQL("insert into student_table VALUES ('iit2012100', 'Vaibhav', 'it1', 1);");
		db.execSQL("insert into student_table VALUES ('iit2012104', 'Aviral Johri', 'it1', 1);");
		db.execSQL("insert into student_table VALUES ('iit2012107', 'Aditya Bharadwaj', 'it1', 1);");
		db.execSQL("insert into student_table VALUES ('iit2012108', 'Sanchit Alekh', 'it1', 1);");

		db.execSQL("insert into student_table VALUES ('iit2012134', 'Shubham Sharma', 'it2', 1);");
		db.execSQL("insert into student_table VALUES ('iit2012162', 'Abhinav Gupta', 'it2', 1);");
		db.execSQL("insert into student_table VALUES ('iit2012163', 'Anmol Jagetia', 'it2', 1);");
		db.execSQL("insert into student_table VALUES ('iit2012203', 'Agrim Khanna', 'it2', 1);");
		db.execSQL("insert into student_table VALUES ('iit2012204', 'Devansh Sharma', 'it2', 1);");

		db.execSQL("insert into student_table VALUES ('iec2012075', 'Sushant Saurabh', 'ece', 1);");
		db.execSQL("insert into student_table VALUES ('iec2012078', 'Vishal Shrivastav', 'ece', 1);");
		db.execSQL("insert into student_table VALUES ('iec2012079', 'Yash Prakash Bharadwaj', 'ece', 1);");
		db.execSQL("insert into student_table VALUES ('iec2012080', 'Amit Kushwaha', 'ece', 1);");
		db.execSQL("insert into student_table VALUES ('iec2012099', 'Ankush Raghuvanshi', 'ece', 1);");

		// Student_course

		db.execSQL("insert into student_course VALUES ('iit2012092', '401');");
		db.execSQL("insert into student_course VALUES ('iit2012092', '402');");
		db.execSQL("insert into student_course VALUES ('iit2012092', '403');");
		db.execSQL("insert into student_course VALUES ('iit2012092', '404');");
		db.execSQL("insert into student_course VALUES ('iit2012092', '405');");

		db.execSQL("insert into student_course VALUES ('iit2012100', '401');");
		db.execSQL("insert into student_course VALUES ('iit2012100', '402');");
		db.execSQL("insert into student_course VALUES ('iit2012100', '403');");
		db.execSQL("insert into student_course VALUES ('iit2012100', '404');");
		db.execSQL("insert into student_course VALUES ('iit2012100', '405');");

		db.execSQL("insert into student_course VALUES ('iit2012104', '401');");
		db.execSQL("insert into student_course VALUES ('iit2012104', '402');");
		db.execSQL("insert into student_course VALUES ('iit2012104', '403');");
		db.execSQL("insert into student_course VALUES ('iit2012104', '404');");
		db.execSQL("insert into student_course VALUES ('iit2012104', '405');");

		db.execSQL("insert into student_course VALUES ('iit2012107', '401');");
		db.execSQL("insert into student_course VALUES ('iit2012107', '402');");
		db.execSQL("insert into student_course VALUES ('iit2012107', '403');");
		db.execSQL("insert into student_course VALUES ('iit2012107', '404');");
		db.execSQL("insert into student_course VALUES ('iit2012107', '405');");

		db.execSQL("insert into student_course VALUES ('iit2012108', '401');");
		db.execSQL("insert into student_course VALUES ('iit2012108', '402');");
		db.execSQL("insert into student_course VALUES ('iit2012108', '403');");
		db.execSQL("insert into student_course VALUES ('iit2012108', '404');");
		db.execSQL("insert into student_course VALUES ('iit2012108', '405');");

		db.execSQL("insert into student_course VALUES ('iit2012134', '401');");
		db.execSQL("insert into student_course VALUES ('iit2012134', '402');");
		db.execSQL("insert into student_course VALUES ('iit2012134', '403');");
		db.execSQL("insert into student_course VALUES ('iit2012134', '404');");
		db.execSQL("insert into student_course VALUES ('iit2012134', '405');");

		db.execSQL("insert into student_course VALUES ('iit2012162', '401');");
		db.execSQL("insert into student_course VALUES ('iit2012162', '402');");
		db.execSQL("insert into student_course VALUES ('iit2012162', '403');");
		db.execSQL("insert into student_course VALUES ('iit2012162', '404');");
		db.execSQL("insert into student_course VALUES ('iit2012162', '405');");

		db.execSQL("insert into student_course VALUES ('iit2012163', '401');");
		db.execSQL("insert into student_course VALUES ('iit2012163', '402');");
		db.execSQL("insert into student_course VALUES ('iit2012163', '403');");
		db.execSQL("insert into student_course VALUES ('iit2012163', '404');");
		db.execSQL("insert into student_course VALUES ('iit2012163', '405');");

		db.execSQL("insert into student_course VALUES ('iit2012203', '401');");
		db.execSQL("insert into student_course VALUES ('iit2012203', '402');");
		db.execSQL("insert into student_course VALUES ('iit2012203', '403');");
		db.execSQL("insert into student_course VALUES ('iit2012203', '404');");
		db.execSQL("insert into student_course VALUES ('iit2012203', '405');");

		db.execSQL("insert into student_course VALUES ('iit2012204', '401');");
		db.execSQL("insert into student_course VALUES ('iit2012204', '402');");
		db.execSQL("insert into student_course VALUES ('iit2012204', '403');");
		db.execSQL("insert into student_course VALUES ('iit2012204', '404');");
		db.execSQL("insert into student_course VALUES ('iit2012204', '405');");

		db.execSQL("insert into student_course VALUES ('iec2012075', '401');");
		db.execSQL("insert into student_course VALUES ('iec2012075', '402');");
		db.execSQL("insert into student_course VALUES ('iec2012075', '403');");
		db.execSQL("insert into student_course VALUES ('iec2012075', '404');");
		db.execSQL("insert into student_course VALUES ('iec2012075', '405');");

		db.execSQL("insert into student_course VALUES ('iec2012078', '401');");
		db.execSQL("insert into student_course VALUES ('iec2012078', '402');");
		db.execSQL("insert into student_course VALUES ('iec2012078', '403');");
		db.execSQL("insert into student_course VALUES ('iec2012078', '404');");
		db.execSQL("insert into student_course VALUES ('iec2012078', '405');");

		db.execSQL("insert into student_course VALUES ('iec2012079', '401');");
		db.execSQL("insert into student_course VALUES ('iec2012079', '402');");
		db.execSQL("insert into student_course VALUES ('iec2012079', '403');");
		db.execSQL("insert into student_course VALUES ('iec2012079', '404');");
		db.execSQL("insert into student_course VALUES ('iec2012079', '405');");

		db.execSQL("insert into student_course VALUES ('iec2012080', '401');");
		db.execSQL("insert into student_course VALUES ('iec2012080', '402');");
		db.execSQL("insert into student_course VALUES ('iec2012080', '403');");
		db.execSQL("insert into student_course VALUES ('iec2012080', '404');");
		db.execSQL("insert into student_course VALUES ('iec2012080', '405');");

		db.execSQL("insert into student_course VALUES ('iec2012099', '401');");
		db.execSQL("insert into student_course VALUES ('iec2012099', '402');");
		db.execSQL("insert into student_course VALUES ('iec2012099', '403');");
		db.execSQL("insert into student_course VALUES ('iec2012099', '404');");
		db.execSQL("insert into student_course VALUES ('iec2012099', '405');");

		// performance_table

		db.execSQL("insert into performance_table VALUES ('iit2012092', '401', 12, 73, 11, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012092', '402', 23, 71, 12, 10, 10, 'very bad');");
		db.execSQL("insert into performance_table VALUES ('iit2012092', '403', 22, 55, 10, 10, 10, 'bad');");
		db.execSQL("insert into performance_table VALUES ('iit2012092', '404', 11, 34, 05, 10, 10, 'average');");
		db.execSQL("insert into performance_table VALUES ('iit2012092', '405', 18, 66, 11, 10, 10, 'bad');");

		db.execSQL("insert into performance_table VALUES ('iit2012100', '401', 22, 74, 11, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012100', '402', 12, 70, 12, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012100', '403', 23, 69, 13, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012100', '404', 24, 68, 14, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012100', '405', 27, 61, 15, 10, 10, 'great');");

		db.execSQL("insert into performance_table VALUES ('iit2012104', '401', 22, 67, 15, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012104', '402', 12, 44, 11, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012104', '403', 25, 55, 10, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012104', '404', 28, 69, 05, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012104', '405', 28, 61, 11, 10, 10, 'great');");

		db.execSQL("insert into performance_table VALUES ('iit2012107', '401', 22, 66, 11, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012107', '402', 23, 73, 12, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012107', '403', 18, 70, 10, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012107', '404', 19, 72, 09, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012107', '405', 20, 62, 11, 10, 10, 'great');");

		db.execSQL("insert into performance_table VALUES ('iit2012108', '401', 22, 67, 15, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012108', '402', 26, 62, 11, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012108', '403', 25, 61, 12, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012108', '404', 23, 65, 13, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012108', '405', 20, 68, 10, 10, 10, 'great');");

		db.execSQL("insert into performance_table VALUES ('iit2012134', '401', 27, 70, 15, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012134', '402', 28, 71, 15, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012134', '403', 27, 70, 15, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012134', '404', 27, 70, 15, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012134', '405', 27, 70, 15, 10, 10, 'great');");

		db.execSQL("insert into performance_table VALUES ('iit2012162', '401', 14, 55, 15, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012162', '402', 13, 74, 14, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012162', '403', 13, 66, 13, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012162', '404', 12, 71, 12, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012162', '405', 11, 74, 11, 10, 10, 'great');");

		db.execSQL("insert into performance_table VALUES ('iit2012163', '401', 22, 67, 15, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012163', '402', 25, 53, 15, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012163', '403', 24, 57, 15, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012163', '404', 23, 75, 15, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012163', '405', 30, 75, 15, 10, 10, 'great');");

		db.execSQL("insert into performance_table VALUES ('iit2012203', '401', 14, 70, 14, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012203', '402', 28, 71, 13, 10, 10, 'very bad');");
		db.execSQL("insert into performance_table VALUES ('iit2012203', '403', 24, 57, 10, 10, 10, 'bad');");
		db.execSQL("insert into performance_table VALUES ('iit2012203', '404', 23, 55, 05, 10, 10, 'average');");
		db.execSQL("insert into performance_table VALUES ('iit2012203', '405', 18, 68, 11, 10, 10, 'bad');");

		db.execSQL("insert into performance_table VALUES ('iit2012204', '401', 14, 70, 15, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012204', '402', 14, 71, 12, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012204', '403', 14, 72, 13, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012204', '404', 14, 73, 14, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iit2012204', '405', 14, 74, 10, 10, 10, 'great');");

		db.execSQL("insert into performance_table VALUES ('iec2012075', '401', 22, 68, 15, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012075', '402', 21, 57, 08, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012075', '403', 18, 60, 13, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012075', '404', 15, 57, 12, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012075', '405', 20, 73, 11, 10, 10, 'great');");

		db.execSQL("insert into performance_table VALUES ('iec2012078', '401', 30, 67, 11, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012078', '402', 12, 71, 12, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012078', '403', 22, 43, 13, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012078', '404', 27, 55, 10, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012078', '405', 25, 65, 11, 10, 10, 'great');");

		db.execSQL("insert into performance_table VALUES ('iec2012079', '401', 22, 74, 09, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012079', '402', 29, 55, 14, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012079', '403', 12, 34, 13, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012079', '404', 25, 57, 12, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012079', '405', 27, 68, 11, 10, 10, 'great');");

		db.execSQL("insert into performance_table VALUES ('iec2012080', '401', 21, 72, 12, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012080', '402', 25, 57, 11, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012080', '403', 29, 70, 05, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012080', '404', 23, 64, 11, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012080', '405', 20, 62, 10, 10, 10, 'great');");

		db.execSQL("insert into performance_table VALUES ('iec2012099', '401', 22, 67, 13, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012099', '402', 15, 59, 07, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012099', '403', 27, 42, 10, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012099', '404', 28, 57, 11, 10, 10, 'great');");
		db.execSQL("insert into performance_table VALUES ('iec2012099', '405', 24, 61, 12, 10, 10, 'great');");

		// class_table
		db.execSQL("insert into class_table values ('it1', '2014-05-12');");
		db.execSQL("insert into class_table values ('it2', '2014-06-20');");
		db.execSQL("insert into class_table values ('ece', '2014-06-25');");

		db.execSQL("PRAGMA foreign_keys = ON");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldv, int newv) {
		db.execSQL("DROP TABLE IF EXISTS " + COURSE_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CLASS_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + TEACHER_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + TEACHER_COURSE);
		db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + STUDENT_COURSE);
		db.execSQL("DROP TABLE IF EXISTS " + PERFORMANCE_TABLE);

		onCreate(db);

	}

}
