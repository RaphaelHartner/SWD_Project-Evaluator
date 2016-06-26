package at.fh.swd14.evaluator.Evaluation;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

class TotalAverageTimeEvaluator extends CommonEvaluator
{

	@Override
	protected String internalExecuteEvaluation()
	{
		double result = 0;
		int counter = folder.listFiles().length;
		for (final File fileEntry : folder.listFiles())
		{
			if (!fileEntry.isDirectory())
			{
				result += getTotalCalcTime(fileEntry);
			}
		}
		return "TotalCalcAverage: " + (result / counter);
	}

	private double getTotalCalcTime(File f)
	{
		try
		{
			List<String> lines = Files.readAllLines(f.toPath());

			if (lines.size() >= 1)
			{
				String value = lines.get(0).split(" ")[3];
				value = value.replace(',', '.');
				try
				{
					return Double.parseDouble(value);
				} catch (Exception e)
				{
					e.printStackTrace();
				}

			}

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
