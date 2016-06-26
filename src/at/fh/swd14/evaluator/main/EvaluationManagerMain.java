package at.fh.swd14.evaluator.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import at.fh.swd14.evaluator.Evaluation.EvaluationType;
import at.fh.swd14.evaluator.Evaluation.Evaluator;

public class EvaluationManagerMain
{

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		if (args.length != 2)
		{
			System.out.println("Usage: <folder> <destination-file>");
			return;
		}

		System.out.println("Choose Evaluation:");
		System.out.println("\t0: TasksPerHost");
		System.out.println("\t1: TotalAverageTime");
		System.out.println("\t2: TasksAverageTime");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		EvaluationType type = EvaluationType.values()[Integer.parseInt(br.readLine())];
		
		Evaluator evaluator = Evaluator.createEvaluator(type);
		evaluator.setSourceFolder(args[0]);
		evaluator.setReportFileName(args[1]);
		evaluator.executeEvaluation();
		
	}

}
