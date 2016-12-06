public class Converter
{
	private double finalResult;
	private boolean metricAlready;
	private MolecularScreen screen;

	private double METRIC_PER_GRAM;
	private final double AMU_PER_GRAM = 1.66 * Math.pow(10,-24);
	private double GRAM_PER_MOL;
	private final double MOL_PER_PARTICLES = 6.02 * Math.pow(10,23);

	public Converter(double a, double given, MolecularScreen mscr, double molarMass)
	{
		METRIC_PER_GRAM = Math.pow(10, a);
		finalResult = given;
		screen = mscr;
		GRAM_PER_MOL = molarMass;
		metricAlready = false;
	}
	//method for testing
	public double getFinalResult()
	{
		return finalResult;
	}

	public void takeCommand(String command)
	{
		String conversion = "";
		if(command.contains("amu -> gram") || command.contains("gram -> amu"))
			conversion = amu_Gram(command);
		else if(command.contains("particles -> gram/mol") || command.contains("gram/mol -> particles"))
			conversion = particles_GramMol(command);
		else if(command.contains("gram -> mol") || command.contains("mol -> gram"))
			conversion = gram_Mol(command);
		else if(command.contains("mol -> particles") || command.contains("particles -> mol"))
			conversion = mol_Particles(command);
		else
			conversion = gram_Metrics(command);
		screen.drawConversions(conversion);
	}

	public void takeCommand(String command, int conversionFactor)
	{//overloaded to handle double metric-metric commands
		metric_Metric_Double(command, conversionFactor);
	}

	private String metric_Metric_Double(String command, int conversionFactor)
	{
		if(command.startsWith("gram"))
		{
			finalResult = finalResult*(1/Math.pow(10,conversionFactor));
				return "1/10^"+conversionFactor;
		}
		finalResult = finalResult*(Math.pow(10,conversionFactor));
		return "10^"+conversionFactor+"/1";
	}

	private String gram_Metrics(String command)
	{
		if(command.startsWith("gram"))
		{
			finalResult = finalResult*(1/METRIC_PER_GRAM);
			return "#1/"+METRIC_PER_GRAM;
		}
		finalResult = finalResult*(METRIC_PER_GRAM);
		return "#"+METRIC_PER_GRAM+"//1";
	}

	private String amu_Gram(String command)
	{
		if(command.startsWith("amu"))
		{
			finalResult = finalResult*(AMU_PER_GRAM);
			return "1.66*10^-24 gram/1 amu";
		}
		finalResult = finalResult*(1/AMU_PER_GRAM);
		return "1 amu/1.66*10^-24 gram";
	}

	private String gram_Mol(String command)
	{
		if(command.startsWith("gram"))
		{
			finalResult = finalResult*(1/GRAM_PER_MOL);
			return "1mol/"+(int)GRAM_PER_MOL+"gram";
		}
		finalResult = finalResult*(GRAM_PER_MOL);
		return (int)GRAM_PER_MOL+"gram/1mol";
	}

	private String mol_Particles(String command)
	{
		if(command.startsWith("mol"))
		{
			finalResult = finalResult*(MOL_PER_PARTICLES);
			return "6.02*10^23 particles/1 mol";
		}
		finalResult = finalResult*(1/MOL_PER_PARTICLES);
		return "1 mol/6.02*10^23 particles";
	}

	private String particles_GramMol(String command)
	{
		if(command.startsWith("particles"))
		{
			finalResult = finalResult*(GRAM_PER_MOL);
			return (int)GRAM_PER_MOL+" gram_mol/1 particle";
		}
		finalResult = finalResult*(1/GRAM_PER_MOL);
		return "1 particle/"+(int)GRAM_PER_MOL+" gram/mol";
	}
}