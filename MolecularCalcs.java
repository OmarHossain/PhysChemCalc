import java.util.Scanner;

public class MolecularCalcs extends Calculator
{
	private String inputMolecule, unitsInit, unitsFin, s;
	private double givenValue;
	private Scanner termReader;
	private Molecule molecule;
	private MolecularScreen screen;
	private MolecularMath math;

	public MolecularCalcs()
	{
		inputMolecule = "";
		unitsInit = "";
		unitsFin = "";
		s = "";
		givenValue = 0.0;
		termReader = new Scanner(System.in);
	}

	public void run()
	{
		//MolecularCalcs mcs = new MolecularCalcs();
		//mcs.initiateSequence();
		initiateSequence();
	}

	public void initiateSequence()
	{
		introduction();
		
		getScienceInformation();
		molecule = new Molecule(inputMolecule);
		molecule.calculateAttr(); //calculate attribute

		screen = new MolecularScreen(molecule); //requires several of molecule's attributes

		math = new MolecularMath(unitsInit,unitsFin,givenValue,molecule);
		math.executePath(screen); //handles math and prints to interface
	}

	public void introduction()
	{
		System.out.println("\n\n\n<><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
		System.out.println("                   || MolecularCalcs ||");
		System.out.println("|| This program will help you with dimensional analysis ||");
		System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><>\n");
	}

	public void getScienceInformation()
	{
		//System.out.print("--> Enter your molecule/atom (ex: C6H12O6/ex: Na) : ");
		//inputMolecule = termReader.nextLine();
		inputMolecule = Prompt.getString("--> Enter your molecule/atom (ex: C6H12O6/ex: Na) : ");
		System.out.println();
		unitsInit = Prompt.getString("\n--> Enter Initial Units (mass[imperial units only, and: gram, gram/mol], molecules/atoms, mol, amu) : ");
		System.out.println();
		s = Prompt.getString("\n--> Enter acquired value/measurement : ");
		System.out.println();
		symbolReader();
		System.out.print("\n--> Enter Final Units (mass[imperial units only, and: gram, gram/mol], molecules/atoms, mol, amu) : ");
		unitsFin = termReader.nextLine();
	}

	public void symbolReader()
	{
		if(s.contains("^") && !s.contains("*")) //ex: 15^16
			givenValue = Math.pow(Double.parseDouble(s.substring(0,s.indexOf("^"))),Double.parseDouble(s.substring(s.indexOf("^")+1)));
		else if(s.contains("^") && s.contains("*")) //ex: 2.3*10^17 (scientific notation)
			givenValue = Double.parseDouble(s.substring(0,s.indexOf("*")))*Math.pow(Double.parseDouble(s.substring(s.indexOf("*")+1,s.indexOf("^"))),Double.parseDouble(s.substring(s.indexOf("^")+1)));
		else //ex: 15.16
			givenValue = Double.parseDouble(s);
	}
}