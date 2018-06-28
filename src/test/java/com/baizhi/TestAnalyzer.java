package com.baizhi;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

public class TestAnalyzer {
    String text = "传智播客百知教育陈艳男我与父亲不相见已二年余了，我最不能忘记的是他的背影。那年冬天，祖母死了，父亲的差使也交卸了，正是祸不单行的日子，我从北京到徐州，打算跟着父亲奔丧回家。到徐州见着父亲，看见满院狼藉的东西，又想起祖母，不禁簌簌地流下眼泪";

    @Test
    public void testStanderAnalyzer() throws IOException {
       test(new StandardAnalyzer(Version.LUCENE_44),text);
        System.out.println(StandardAnalyzer.STOP_WORDS_SET);
    }
    //中日韩分词器  二分分词器
    @Test
    public void testCJKAnalyzer() throws IOException {
        test(new CJKAnalyzer(Version.LUCENE_44),text);
    }

    //智能中文分词器  自定义的目的
    @Test
    public void testSmartChineseAnalyzer() throws IOException {
        test(new SmartChineseAnalyzer(Version.LUCENE_44),text);
    }
   //ik分词器  庖丁解牛   自定义切分词  自定义语气词
   @Test
   public void testIKAnalyzer() throws IOException {
       test(new IKAnalyzer(),text);
   }




    public static void test(Analyzer analyzer, String text) throws IOException {

        System.out.println("当前分词器:--->" + analyzer.getClass().getName());

        TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));

        tokenStream.addAttribute(CharTermAttribute.class);

        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            CharTermAttribute attribute = tokenStream.getAttribute(CharTermAttribute.class);
            System.out.println(attribute.toString());
        }

        tokenStream.end();
    }
}
