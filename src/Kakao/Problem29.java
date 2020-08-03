/*
 * 2019 KAKAO BLIND RECRUITMENT
 * https://programmers.co.kr/learn/courses/30/lessons/42893
 * 매칭 점수
*/
package Kakao;

import java.util.*;
import java.util.regex.*;

public class Problem29 {
    static Pattern wordPattern;
    static Pattern urlPattern;
    static Pattern hrefPattern;

    static class Web implements Comparable<Web> {
	String url;
	List<String> hrefURL;
	List<Web> hrefWeb;
	double basicScore;
	double outLinkNumber;
	double outLinkScore;
	double matchingScore;
	int idx;

	public Web(String page, int idx) {
	    hrefURL = new ArrayList<>();
	    hrefWeb = new LinkedList<>();
	    this.url = getURL(page);
	    this.basicScore = getBasicScore(page);
	    this.outLinkNumber = getOutLinkNumber(page);
	    this.outLinkScore = 0;
	    this.idx = idx;
	}

	private String getURL(String page) {
	    Matcher m = urlPattern.matcher(page);
	    return m.find() ? m.group() : null;
	}

	private int getOutLinkNumber(String page) {
	    Matcher m = hrefPattern.matcher(page);
	    int cnt = 0;
	    while (m.find()) {
		hrefURL.add(m.group());
		cnt++;
	    }
	    return cnt;
	}

	private int getBasicScore(String page) {
	    Matcher m = wordPattern.matcher(page);
	    int cnt = 0;
	    while (m.find()) {
		cnt++;
	    }
	    return cnt;
	}

	public void mappingWeb(List<Web> webs) {
	    for (Web web : webs) {
		if (web.hrefURL.contains(url)) {
		    hrefWeb.add(web);
		}
	    }
	}

	public double getLinkScore() {
	    if (outLinkScore > 0)
		return outLinkScore;
	    double score = 0;
	    for (Web web : hrefWeb) {
		score += (web.basicScore / web.outLinkNumber);
	    }
	    outLinkScore = score;
	    matchingScore = basicScore + outLinkScore;
	    return score;
	}

	@Override
	public int compareTo(Web o) {
	    return Double.compare(o.matchingScore, matchingScore) == 0 ? Double.compare(idx, o.idx)
		    : Double.compare(o.matchingScore, matchingScore);

	}
    }

    public static int solution(String word, String[] pages) {
	wordPattern = Pattern.compile("(?<=[^a-zA-Z])(?i)" + word + "(?=\\b|[0-9])|(^(?i)"+word+"\b)");
	urlPattern = Pattern.compile("(?<=\\<meta.*content=\\\"https://).*(?=\\\"/\\>)");
	hrefPattern = Pattern.compile("(?<=\\<a\\shref.+https://).*?(?=\\\")");
	
	List<Web> webs = new LinkedList<>();
	int idx = 0;
	for (String page : pages) {
	    webs.add(new Web(page, idx++) );
	}

	for (Web web : webs) {
	    web.mappingWeb(webs);
	}

	for (Web web : webs){
	    web.getLinkScore();
	}
	return webs.stream().sorted().findFirst().get().idx;
    }

    public static void main(String[] args) {
	int answer = 0;
	answer = solution("blind", new String[] {
		"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
		"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
		"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>" });
	System.out.println(answer);

	answer = solution("Muzi", new String[] {
		"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
		"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2\n\n\t^\n</body>\n</html>" });
	System.out.println(answer);
    }
}
