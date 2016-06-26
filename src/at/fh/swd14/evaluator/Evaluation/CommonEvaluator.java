package at.fh.swd14.evaluator.Evaluation;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public abstract class CommonEvaluator implements Evaluator
{

	protected File folder;
	protected File destFile;

	@Override
	public void setSourceFolder(String folder)
	{
		this.folder = new File(folder);
	}

	@Override
	public void setReportFileName(String filename)
	{
		this.destFile = new File(filename);
	}

	@Override
	public void executeEvaluation()
	{

		try
		{
			String message = internalExecuteEvaluation();
			if (!destFile.exists())
			{
				destFile.createNewFile();
			}

			Files.write(destFile.toPath(), (destFile.getName() + "\n").getBytes(), StandardOpenOption.APPEND);
			Files.write(destFile.toPath(), message.getBytes(), StandardOpenOption.APPEND);
			Files.write(destFile.toPath(), "\n\n".getBytes(), StandardOpenOption.APPEND);
			System.out.println(message);

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected abstract String internalExecuteEvaluation();

}
