package at.fh.swd14.evaluator.Evaluation;

public interface Evaluator
{
	public static Evaluator createEvaluator(EvaluationType type)
	{
		switch (type)
		{
			case CountTasksPerHost:
				return new TasksPerHostEvaluator();
			case CalcTotalAverageTime:
				return new TotalAverageTimeEvaluator();
			case CalcTasksAverageTime:
				return new TasksAverageTimeEvaluator();
			default:
				throw new UnsupportedOperationException("Choosen Algorithm isn't implemented yet!");
		}
	}

	void setSourceFolder(String folder);

	void setReportFileName(String filename);

	void executeEvaluation();
}
