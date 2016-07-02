package at.fh.swd14.evaluator.Evaluation;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TasksPerHostEvaluator extends CommonEvaluator
{
	@Override
	protected String internalExecuteEvaluation()
	{
		Map<String, Integer> workerCounter = new HashMap<String, Integer>();

		int counter = folder.listFiles().length;
		for (final File fileEntry : folder.listFiles())
		{
			if (!fileEntry.isDirectory())
			{
				updateHashMap(fileEntry, workerCounter);
			}
		}

		return MapToMessage(workerCounter, counter);
	}

	private void updateHashMap(File f, Map<String, Integer> workerCounter)
	{
		try
		{
			List<String> lines = Files.readAllLines(f.toPath());
			String worker = "";

			if (lines.size() > 1)
			{

				for (int i = 1; i < lines.size(); i++)
				{
					worker = lines.get(i).split("\t")[colCount-1];
					if (!workerCounter.containsKey(worker))
					{
						workerCounter.put(worker, 1);
					} else
					{
						Integer currentCount = workerCounter.get(worker);
						currentCount++;
						workerCounter.put(worker, currentCount);
					}
				}
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private String MapToMessage(Map<String, Integer> workerCounter, Integer fileCount)
	{
		String message = "";
		for (Map.Entry<String, Integer> worker : workerCounter.entrySet())
		{
			message += worker.getKey().replace(',', '.') + "\t" + ((double)worker.getValue() / fileCount) + "\n";
		}

		return message;
	}

}
