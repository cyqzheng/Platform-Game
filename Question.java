/*
Name: Michael Li, Crystal Yan
Date: January 7, 2018
Class code: ICS 2O3 - 02
Program name: Question
An Object that represents a question asked by the treasure chests.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class Question
{
    private Graphics g1; // The Graphics that will draw stuff
    private Console c; // The input/output console
    private boolean mulChoice; // Whether or not the question is multiple-choice
    private boolean inputInt; // Whether or not the user should input an integer
    private String quest; // The question that will be asked
    private String ansA; // The answer given for choice A
    private String ansB; // The answer given for choice B
    private String ansC; // The answer given for choice C
    private String ansD; // The answer given for choice D
    private String ansE; // The answer given for choice E
    private String sol; // The solution that is displayed after the user answers the question
    private int ans1; // The answer if the question has an integer answer
    private double ans2; // The answer if the question has a real number answer
    private char ans3; // The answer if the question is multiple-choice
    private char ans4; // ans3 in uppercase

    private final int NUM_EASY = 16; // The number of easy questions
    private final int NUM_HARD = 12; // The number of hard questions

    public Question (Console c1, Graphics g, boolean hard)  // Constructs a random question
    {
	// Assign variables
	c = c1;
	g1 = g;

	// Generate question
	if (hard)
	{
	    // Select random hard question
	    int r = (int) (Math.random () * NUM_HARD);
	    switch (r)
	    {
		case 0:
		    mulChoice = false;
		    inputInt = false;
		    quest = "What is the value of the infinite series 1/6 + 1/36 + 1/216 + ... , where the";
		    quest += "\nnth term is the reciprocal of the nth power of 6?";
		    ans2 = 0.2;
		    sol = "Let the answer to this question be X. Then, 1/36 + 1/216 + 1/1296 + ... = X/6.";
		    sol += "\nThus, X = 1/6 + X/6. Solving, you get 5X/6 = 1/6, so X = 0.2.";
		    break;
		case 1:
		    mulChoice = false;
		    inputInt = true;
		    quest = "There is a school with 1,000 students and 1,000 lockers. On the first day of";
		    quest += "\nterm the headteacher asks the first student to go along and open every single";
		    quest += "\nlocker; he asks the second student to go to every second locker and close it;";
		    quest += "\nthe third student to go to every third locker and close it if it is open or open";
		    quest += "\nit if it is closed; the fourth to go to every fourth locker and so on. The";
		    quest += "\nprocess is completed with the thousandth student. How many lockers are open at";
		    quest += "\nthe end?";
		    ans1 = 31;
		    sol = "The state of each locker changes for each factor of the locker number. Thus, a";
		    sol += "\nlocker will be left open if and only if its number has an odd number of factors.";
		    sol += "\nFor each divisor d of the locker number n, we can pair d with another divisor";
		    sol += "\nn / d unless d = n / d. d = n / d will occur if and only if n = d^2, implying";
		    sol += "\nthat n is a perfect square and d is the square root of n. This will happen";
		    sol += "\nexactly once if n is a perfect square, and this will never happen if n is not a";
		    sol += "\nperfect square. Thus, n has an odd number of divisors if and only if n is a";
		    sol += "\nperfect square. The highest perfect square below 1,000 is 961 = 31^2, so there";
		    sol += "\nare 31 perfect squares below 1,000, so our answer is 31.";
		    break;
		case 2:
		    mulChoice = true;
		    inputInt = false;
		    quest = "Find all values of the parameter m (a real number) such that the equation";
		    quest += "\n2x^2 - mx + m = 0 has no real solutions. Write the answer as an interval.";
		    ansA = "[0 , 2]";
		    ansB = "(-2 , 0)";
		    ansC = "[0 , 8]";
		    ansD = "No such m exists.";
		    ansE = "(0 , 8)";
		    ans3 = 'e';
		    ans4 = 'E';
		    sol = "A quadratic equation ax^2 + bx + c has no real solutions if and only if its";
		    sol += "\ndiscriminant, b^2 - 4ac, is negative. In this question, we have a = 2, b = -m,";
		    sol += "\nand c = m. Plugging this into the discriminant formula, we get the inequality";
		    sol += "\nm^2 - 8m < 0. Factoring, we get m(m - 8) < 0. This happens when exactly one of";
		    sol += "\nm and m - 8 is negative. Note that we cannot have m negative and m - 8 positive,";
		    sol += "\nso m must be positive and m - 8 must be negative. Then, m > 0 and m - 8 < 0.";
		    sol += "\nm - 8 < 0 gives us m < 8, so we get the compound inequality 0 < m < 8. Thus, m";
		    sol += "\nis in the interval (0 , 8) => (E).";
		    break;
		case 3:
		    mulChoice = false;
		    inputInt = true;
		    quest = "m and n are integers such that 4^m / 125 = 5^n / 64. Find the value of m + n.";
		    ans1 = -6;
		    sol = "Cross-multiplying, we get 64 * 4^m = 125 * 5^n. Then, 4^3 * 4^m = 5^3 * 5^n.";
		    sol += "\nUsing the exponent rules, we get 4^(m + 3) = 5^(n + 3). 4 and 5 are not powers";
		    sol += "\nof each other, so this can only occur when 4^(m + 3) = 5^(n + 3) = 1. Then,";
		    sol += "\nm + 3 = n + 3 = 0. Thus, m = n = -3, so m + n = -6.";
		    break;
		case 4:
		    mulChoice = false;
		    inputInt = false;
		    quest = "Find the area between the two concentric circles defined by:";
		    quest += "\n\tx^2 + y^2 - 2x + 4y + 1 = 0";
		    quest += "\nand:";
		    quest += "\n\tx^2 + y^2 - 2x + 4y - 11 = 0.";
		    ans2 = 37.7;
		    sol = "We will complete the square for the first equation:";
		    sol += "\nx^2 + y^2 - 2x + 4y + 1 = 0";
		    sol += "\n(x^2 - 2x) + (y^2 + 4y) + 1 = 0";
		    sol += "\n[x^2 - 2x + (-1)^2 - (-1)^2] + (y^2 + 4y + 2^2 - 2^2) + 1 = 0";
		    sol += "\n[(x - 1)^2 - 1] + [(y + 2)^2 - 4] + 1 = 0";
		    sol += "\n(x - 1)^2 + (y + 2)^2 - 4 = 0";
		    sol += "\n(x - 1)^2 + (y + 2)^2 = 4";
		    sol += "\nThus, the square of the first circle's radius is 4, so its area is 4 pi.";
		    sol += "\nApplying a similar process to the second circle, we get:";
		    sol += "\n(x - 1)^2 + (y + 2)^2 = 16.";
		    sol += "\nThus, the square of the second circle's radius is 16, so its area is 16 pi.";
		    sol += "\nThen, the area between the two circles is the difference between the two areas,";
		    sol += "\nwhich is 16 pi - 4 pi = 12 pi = 37.70.";
		    break;
		case 5:
		    mulChoice = false;
		    inputInt = true;
		    quest = "There are nine balls. One is slightly lighter than the rest, which all have";
		    quest += "\ncompletely equal weights. Using a basic two-sided scale, what's the minimum";
		    quest += "\nnumber of times you need to weigh balls to guarantee that you can find the light";
		    quest += "\none? You can put multiple balls on each side of the scale.";
		    ans1 = 2;
		    sol = "Put a group of three balls on each side of the scale. If the two sides have";
		    sol += "\nequal weights, then the light ball must be in the remaining group of three";
		    sol += "\nballs. Otherwise, the light ball must be in the group of three balls on the";
		    sol += "\nlighter side of the scale. Either way, we will have to analyze a group of three";
		    sol += "\nballs to find the light one. In the selected group of three balls, choose one";
		    sol += "\nball to be placed on each side of the scale. If the two balls have equal";
		    sol += "\nweights, the third ball must be the light ball. Otherwise, the light ball will";
		    sol += "\nbe the ball on the lighter side of the scale. You will have to weigh balls a";
		    sol += "\nmaximum of 2 times.";
		    break;
		case 6:
		    mulChoice = false;
		    inputInt = false;
		    quest = "Let x be the answer to this question. What is the value of 3.61x^2 - 18x + 25?";
		    ans2 = 2.63;
		    sol = "Since x is the value of 3.61x^2 - 18x + 25, we get 3.61x^2 - 18x + 25 = x. Then,";
		    sol += "\nwe move all terms to the left side to get 3.61x^2 - 19x + 25 = 0. Note that this";
		    sol += "\nequation can be factored into (1.9x - 5)^2 = 0. This implies that 1.9x - 5 = 0,";
		    sol += "\nso x = 5 / 1.9 = 2.63.";
		    break;
		case 7:
		    mulChoice = false;
		    inputInt = true;
		    quest = "Anna takes a bizarre math contest which has 10 problems, each of which are worth";
		    quest += "\nexactly 17 points. She receives a score of 51. It is not possible to receive";
		    quest += "\npartial credit for any of the problems. How many different combinations of";
		    quest += "\nproblems could Anna have gotten correct to receive her score of 51?";
		    ans1 = 120;
		    sol = "There are 10 possibilities for the first problem that Anna got correct, 9";
		    sol += "\npossibilities for the second problem she got correct, and 8 possibilities for";
		    sol += "\nthe third problem she got correct. This gives us 10 * 9 * 8 = 720 possibilities";
		    sol += "\nin total. However, each combination is overcounted because there are 6 possible";
		    sol += "\npermutations for each desired combination. Thus, we need to divide by 6, so our";
		    sol += "\nanswer is 720 / 6 = 120.";
		    break;
		case 8:
		    mulChoice = false;
		    inputInt = true;
		    quest = "How many positive integers n are there such that n < 2018 and gcd (n, 2018) = 1?";
		    ans1 = 1008;
		    sol = "First, we prime factorize 2018 into 2 * 1009. Then, if gcd (n, 2018) = 1, n";
		    sol += "\ncannot be divisible by 2 or 1009. First, n is not divisible by 2, so n is odd.";
		    sol += "\nThere are 2018 / 2 = 1009 odd numbers below 2018. Next, n cannot be divisible by";
		    sol += "\n1009. There is only one odd number below 2018 that is divisible by 1009, namely";
		    sol += "\n1009 itself, so we subtract 1 from 1009 to get our answer of 1008.";
		    sol += "\nTo learn more about this problem, search up Euler's Totient Function on Google.";
		    break;
		case 9:
		    mulChoice = false;
		    inputInt = true;
		    quest = "What is 5530^911 modulo 911?";
		    ans1 = 64;
		    sol = "First, we note that 911 is a prime number. Then, by Fermat's Little Theorem,";
		    sol += "\n5530^911 is congruent to 5530 modulo 911. Thus, our answer is 5530 modulo 911,";
		    sol += "\nwhich equals 64.";
		    break;
		case 10:
		    mulChoice = false;
		    inputInt = true;
		    quest = "What is the maximum degree of a polynomial equation representing a hyperbola?";
		    ans1 = 2;
		    sol = "A hyperbola is a type of conic, and all conics can be represented with a";
		    sol += "\npolynomial equation of degree at most 2, according to some sketchy Olympiads";
		    sol += "\nSchool mathematics.";
		    break;
		case 11:
		    mulChoice = true;
		    quest = "What is the value of e^(i * pi / 2)?";
		    ansA = "-1";
		    ansB = "i";
		    ansC = "pi * sqrt(e)";
		    ansD = "i * sin(e)";
		    ansE = "This mysterious number does not exist.";
		    ans3 = 'b';
		    ans4 = 'B';
		    sol = "We can solve this problem by using Euler's famous equation, e^(i * pi) + 1 = 0.";
		    sol += "\nFrom this, we can get e^(i * pi) = -1. This is useful because e^(i * pi / 2) is";
		    sol += "\nsimply the square root of e^(i * pi). Thus, the answer is sqrt(-1) = i => (B).";
		    break;
	    }
	}
	else
	{
	    // Select random easy question
	    int r = (int) (Math.random () * NUM_EASY);
	    switch (r)
	    {
		case 0:
		    mulChoice = true;
		    quest = "In Java, what happens if you try to output 3.0 / 0.0?";
		    ansA = "An ArithmeticException gets thrown.";
		    ansB = "The console displays \"Infinity\".";
		    ansC = "The console displays \"NaN\".";
		    ansD = "You get a compiler error.";
		    ansE = "The computer either explodes or shoots an ArithmeticException at you. It\n\tdepends on the company that designed your computer.";
		    ans3 = 'b';
		    ans4 = 'B';
		    sol = "Java supports division by zero with real numbers. 3.0 and 0.0 are real numbers.";
		    sol += "\nTherefore, the computer will not crash. Instead, the computer will display";
		    sol += "\n\"Infinity\" => (B).";
		    break;
		case 1:
		    mulChoice = true;
		    quest = "What is the period of the equation y = 3sin(1/2(x-3))+5 in radians?";
		    ansA = "4";
		    ansB = "pi";
		    ansC = "2 pi";
		    ansD = "4 pi";
		    ansE = "-3 pi";
		    ans3 = 'd';
		    ans4 = 'D';
		    sol = "The period of the function y = sin(x) is 2 pi radians. The coefficient of 1/2 in";
		    sol += "\nfront of the x means that there is a horizontal stretch by a factor of 2. Thus,";
		    sol += "\nthe old period gets doubled, and the answer is 4 pi => (D).";
		    break;
		case 2:
		    mulChoice = false;
		    inputInt = false;
		    quest = "What is the geometric mean of 24 and 6?";
		    ans2 = 12;
		    sol = "You can find the geometric mean of two numbers by multiplying the two numbers";
		    sol += "\ntogether and taking the square root of the product. In this case, the product of";
		    sol += "\nthe two numbers is 144, so the answer is the square root of 144, which is 12.";
		    break;
		case 3:
		    mulChoice = true;
		    quest = "What is a method in Java?";
		    ansA = "The Method Of Life.";
		    ansB = "A technique that you use to program efficiently.";
		    ansC = "An advanced data type that is versatile and has very many behaviours.";
		    ansD = "A piece of code that runs whenever it is called.";
		    ansE = "A technique that the rich dudes use to organize large-scale coding projects.";
		    ans3 = 'd';
		    ans4 = 'D';
		    sol = "The answer is D. This should be basic Java terminology.";
		    break;
		case 4:
		    mulChoice = false;
		    inputInt = true;
		    quest = "A begger on the street can make one cigarette out of every 6 cigarette butts he";
		    quest += "\nfinds. After one whole day of searching and checking public ashtrays the begger";
		    quest += "\nfinds a total of 72 cigarette butts. How many whole cigarettes can he make and";
		    quest += "\nsmoke from the butts he found?";
		    ans1 = 12;
		    sol = "The begger has 72 cigarette butts, and he needs 6 cigarette butts to make each";
		    sol += "\ncigarette. Thus, our answer is 72 / 6 = 12.";
		    break;
		case 5:
		    mulChoice = true;
		    quest = "What is 4! ?";
		    ansA = "24";
		    ansB = "An emphatic 4.";
		    ansC = "20";
		    ansD = "22";
		    ansE = "19";
		    ans3 = 'a';
		    ans4 = 'A';
		    sol = "4! denotes 4 factorial, which is the product of all the positive integers up to";
		    sol += "\n4. This is equal to 1 * 2 * 3 * 4 = 24 => (A).";
		    break;
		case 6:
		    mulChoice = true;
		    quest = "What is 22.0/11.0 in Java?";
		    ansA = "2.00";
		    ansB = "2";
		    ansC = "20";
		    ansD = "2,0";
		    ansE = "2.0";
		    ans3 = 'e';
		    ans4 = 'E';
		    sol = "In regular math, 22.0 / 11.0 = 2. Additionally, 22.0 and 11.0 are real numbers,";
		    sol += "\nso our answer should be a real number. Thus, the answer is 2.0 => (E).";
		    break;
		case 7:
		    mulChoice = true;
		    quest = "What is the output of Math.sqrt (289) in Java?";
		    ansA = "17";
		    ansB = "19.0";
		    ansC = "17.0";
		    ansD = "144.5";
		    ansE = "982";
		    ans3 = 'c';
		    ans4 = 'C';
		    sol = "The method Math.sqrt (double x) returns the square root of x. Thus, the return";
		    sol += "\nvalue will be the square root of 289, which equals 17. Additionally, the return";
		    sol += "\nvalue will be a real number variable, so the program will output 17.0 => (C).";
		    break;
		case 8:
		    mulChoice = true;
		    quest = "What is the output of Math.pow (16, 4) in Java?";
		    ansA = "65536";
		    ansB = "63002.0";
		    ansC = "164";
		    ansD = "65878";
		    ansE = "65536.0";
		    ans3 = 'e';
		    ans4 = 'E';
		    sol = "The method Math.pow (double a, double b) returns a^b. Thus, the return value";
		    sol += "\nwill be 16^4 = 16 * 16 * 16 * 16 = 65536. Additionally, the return value will be";
		    sol += "\na real number variable, so the program will output 65536.0 => (E).";
		    break;
		case 9:
		    mulChoice = true;
		    quest = "What does if(!(a >= b)) mean in Java?";
		    ansA = "if a is greater than or equal to b";
		    ansB = "if a is less than or equal to b";
		    ansC = "if a is equal to b";
		    ansD = "if a is less than b";
		    ansE = "if a is not greater than b";
		    ans3 = 'd';
		    ans4 = 'D';
		    sol = "The code means \"If a is not greater than or equal to b\". This simplifies to \"If";
		    sol += "\na is less than b\" => (D).";
		    break;
		case 10:
		    mulChoice = true;
		    quest = "What is the name of the intersection point of the three perpendcular bisectors";
		    quest += "\nof a triangle?";
		    ansA = "Circumcircle";
		    ansB = "Centroid";
		    ansC = "No such point exists.";
		    ansD = "Circumcentre";
		    ansE = "Orthocentre";
		    ans3 = 'd';
		    ans4 = 'D';
		    sol = "The answer is D. This should be basic math terminology.";
		    break;
		case 11:
		    mulChoice = false;
		    inputInt = true;
		    quest = "It's dark. You have been struggling to finish your homework projects at 2:30 in";
		    quest += "\nthe morning, and the power suddenly went out. Then, your Asian parents enter";
		    quest += "\nyour room and give you a strange task. You have ten grey socks and ten blue";
		    quest += "\nsocks you need to put into pairs. All socks are exactly the same except for";
		    quest += "\ntheir colour. How many socks would you need to take with you to ensure you had";
		    quest += "\nat least one pair?";
		    ans1 = 3;
		    sol = "If you take 2 socks, then it is possible to take one grey sock and one blue";
		    sol += "\nsock, which you cannot put into pairs. Thus, your desired result would not be";
		    sol += "\nguaranteed. However, if you take 3 socks, then by the Pigeonhole Principle, at";
		    sol += "\nleast two of the socks will have the same colour, so you will always have at";
		    sol += "\nleast one pair of socks. Thus, the answer is 3.";
		    break;
		case 12:
		    mulChoice = false;
		    inputInt = false;
		    quest = "A bat and a ball cost one dollar and ten cents in total. The bat costs a dollar";
		    quest += "\nmore than the ball. How many cents does the ball cost?";
		    ans2 = 5;
		    sol = "Let x be the cost of the bat, in cents.";
		    sol += "\nLet y be the cost of the ball, in cents.";
		    sol += "\nThen, we can get the two equations x + y = 110 and x = y + 100. Substituting the";
		    sol += "\nsecond equation into the first equation, we get y + 100 + y = 110. Combining";
		    sol += "\nlike terms together, we get 2y = 10, so y = 5. Thus, the ball costs 5 cents.";
		    break;
		case 13:
		    mulChoice = false;
		    inputInt = false;
		    quest = "In a lake, there is a patch of lily pads. Every day, the patch doubles in size.";
		    quest += "\nIf it takes 48 days for the patch to cover the entire lake, how many days would";
		    quest += "\nit take for the patch to cover half of the lake?";
		    ans2 = 47;
		    sol = "After the patch covers half of the lake, the patch would take one more day to";
		    sol += "\ndouble in size and cover the entire lake. Thus, the patch would cover half the";
		    sol += "\nlake one day before it covers the entire lake, so it would cover half the lake";
		    sol += "\nin 48 - 1 = 47 days.";
		    break;
		case 14:
		    mulChoice = false;
		    inputInt = false;
		    quest = "Let z be the product of the complex number 5.5 + 3.0i and its conjugate. Find";
		    quest += "\nthe sum of the real and complex parts of z.";
		    ans2 = 39.25;
		    sol = "The conjugate of 5.5 + 3.0i is 5.5 - 3.0i. Therefore, z is equal to";
		    sol += "\n(5.5 + 3.0i)(5.5 - 3.0i). By the difference of squares formula, z is equal to";
		    sol += "\n5.5^2 - (3.0i)^2. Then, z = 30.25 - (-9) = 39.25. Thus, the real part of z is";
		    sol += "\n39.25, and the complex part of z is 0, so our answer is 39.25.";
		    break;
		case 15:
		    mulChoice = true;
		    quest = "What connects the circumcentre, orthocentre, and centroid of a triangle?";
		    ansA = "All three points lie in the interior of the triangle.";
		    ansB = "The three points are collinear.";
		    ansC = "None of the three points can be found with simple formulas.";
		    ansD = "All three points oxidize and explode violently if provoked.";
		    ansE = "One point is the same distance from the other two points.";
		    ans3 = 'b';
		    ans4 = 'B';
		    sol = "If you paid attention in Ms. Elamad's class, you would know that the three";
		    sol += "\ncentres of a triangle lie on the same line => (B). And this line is called the";
		    sol += "\nEuler line.";
		    break;
	    }
	}
    }


    public boolean ask ()  // Helper method to ask the question and check the answer
    {
	// Display the question
	c.println (quest);

	// Make decisions based on the type of question
	if (mulChoice)
	{
	    // Display possible answers
	    c.println ("A) " + ansA);
	    c.println ("B) " + ansB);
	    c.println ("C) " + ansC);
	    c.println ("D) " + ansD);
	    c.println ("E) " + ansE);
	    // Loop until the user chooses a valid answer
	    while (true)
	    {
		// Input the user's guess
		char in = c.getChar ();

		// Check answer
		if (in == ans3 || in == ans4) // If the user chose the correct answer
		{
		    c.println ();
		    c.println ("Correct!");
		    c.println (sol);
		    return true;
		}
		else if ((in >= 'a' && in <= 'e') || (in >= 'A' && in <= 'E')) // If the user chose a different answer
		{
		    c.println ();
		    c.println ("Incorrect!");
		    c.println (sol);
		    return false;
		}
	    }
	}
	else if (inputInt)
	{
	    // Input the user's guess
	    int in = readInt ();

	    // Check answer
	    if (in == ans1)
	    {
		c.println ();
		c.println ("Correct!");
		c.println (sol);
		return true;
	    }
	    else
	    {
		c.println ();
		c.println ("Incorrect!");
		c.println (sol);
		return false;
	    }
	}
	else
	{
	    // Prompt for a decimal input
	    c.println ("Please round your answer to 2 decimal places.");

	    // Input the user's guess
	    double in = readDouble ();

	    // Check answer
	    if (in == ans2)
	    {
		c.println ();
		c.println ("Correct!");
		c.println (sol);
		return true;
	    }
	    else
	    {
		c.println ();
		c.println ("Incorrect!");
		c.println (sol);
		return false;
	    }
	}
    } // ask method


    private int readInt ()  // Helper method to input an integer without crashing if the input is invalid
    {
	// Declare variables
	String in;

	// Loop until input is valid
	while (true)
	{
	    try
	    {
		in = c.readLine ();
		return Integer.parseInt (in);
	    }
	    catch (NumberFormatException ex)
	    {
		c.println ("Please input an integer.");
	    }
	}
    } // readInt method


    private double readDouble ()  // Helper method to input a real number without crashing if the input is invalid
    {
	// Declare variables
	String in;

	// Loop until input is valid
	while (true)
	{
	    try
	    {
		in = c.readLine ();
		return Double.parseDouble (in);
	    }
	    catch (NumberFormatException ex)
	    {
		c.println ("Please input a real number.");
	    }
	}
    } // readDouble method
} // Question class
