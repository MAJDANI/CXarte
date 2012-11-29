package com.novedia.talentmap.services;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Class abstract.
 * @author v.dibi
 */
public abstract class ScoreManage {
/**
 * TOOL_PONDERATION.
 */
private static final Double TOOL_PONDERATION = 3.0;
/**
 * FREQUENCY_USE_PONDERATION.
 */
private static final Double FREQUENCY_USE_PONDERATION = 1.0;
/**
 * NO_USING_TIME_PONDERATION.
 */
private static final Double NO_USING_TIME_PONDERATION = 5.0;

/**
 * This method make statistics.
 * @param scoreT a list toolScore
 * @param nbTool a number of tool
 * @return scocre of concept
 */
public static double conceptScore(final List<Integer> scoreT,
final double nbTool) {

DecimalFormat df = new DecimalFormat();
double resultToolScore = 0;
df.setMaximumFractionDigits(2);
df.setMinimumFractionDigits(2);
df.setDecimalSeparatorAlwaysShown(true);

double toolKnown = scoreT.size() / nbTool;

for (double toolScore : scoreT) {
resultToolScore += toolScore;
}

String strScore = df.format(resultToolScore * toolKnown);

double result = Double.parseDouble(strScore.replace(",", "."));

return result / nbTool;
}

/**
 * @param toolS tool Score.
 * @param fUScore a frequently use score
 * @param noUsingTimeScore a noUsingTimeScore
 * @return a score from a Tool
 */
public static double toolScore(final double toolS, final double fUScore,
final double noUsingTimeScore) {

int j = 4;
int noUsingTimeInverse = 0;

for (int i = 1; i < 5; i++) {
if (i == noUsingTimeScore) {
noUsingTimeInverse = j;
}
j--;
}

return Math.round((TOOL_PONDERATION * toolS
+ FREQUENCY_USE_PONDERATION * fUScore 
+ NO_USING_TIME_PONDERATION
* noUsingTimeInverse)
/ (TOOL_PONDERATION + FREQUENCY_USE_PONDERATION + NO_USING_TIME_PONDERATION));
}
}