package com.project.dto;

import java.util.Collection;

public class AnswerResult {

	private int totalAnswers;
	private Collection<OptionCount> results;
	

	public int getTotalAnswers() {
		return totalAnswers;
	}
	public void setTotalAnswers(int totalAnswers) {
		this.totalAnswers = totalAnswers;
	}
	public Collection<OptionCount> getResults() {
		return results;
	}
	public void setResults(Collection<OptionCount> results) {
		this.results = results;
	}
}