package com.baizhi;

import com.baizhi.util.LuceneUtil;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class LuceneTest {


    /*
     * 测试Lucene创建索引
     * */
    @Test
    public void createIndex() throws IOException {
        //创建indexWriter
        Directory directory = FSDirectory.open(new File("f:/index"));
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_44, new StandardAnalyzer(Version.LUCENE_44));
        IndexWriter indexWriter = new IndexWriter(directory, conf);
        //添加document
        Document document = new Document();
        document.add(new StringField("id", "1", Field.Store.YES));
        document.add(new StringField("name", "背影", Field.Store.YES));
        document.add(new StringField("author", "朱自清", Field.Store.YES));
        document.add(new StringField("date", "2015-3-2", Field.Store.YES));
        indexWriter.addDocument(document);
        //提交并关闭indexWriter
        indexWriter.commit();
        indexWriter.close();
    }

    /*
     * 测试Lucene查询
     * */
    @Test
    public void searchIndex() throws IOException {

        Directory directory = FSDirectory.open(new File("f:/index"));
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        Term term = new Term("id", "1");
        Query query = new TermQuery(term);
        TopDocs topDocs = indexSearcher.search(query, 100);


        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int docID = scoreDoc.doc;
            Document document = indexSearcher.doc(docID);
            System.out.println(document.get("id"));
            System.out.println(document.get("name"));
            System.out.println(document.get("author"));
            System.out.println(document.get("date"));
        }
    }

    /*
     * 测试Lucene更新索引
     * */
    @Test
    public void updateIndex() throws IOException {
        //创建indexWriter
        Directory directory = FSDirectory.open(new File("f:/index"));
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_44, new StandardAnalyzer(Version.LUCENE_44));
        IndexWriter indexWriter = new IndexWriter(directory, conf);
        //添加document
        Document document = new Document();
        document.add(new StringField("id", "1", Field.Store.YES));
        document.add(new StringField("name", "qqq背影", Field.Store.YES));
        document.add(new StringField("author", "qqq朱自清", Field.Store.YES));
        document.add(new StringField("date", "qqq2015-3-2", Field.Store.YES));
        indexWriter.updateDocument(new Term("id", "1"), document);
        //提交并关闭indexWriter
        indexWriter.commit();
        indexWriter.close();
    }


    /*
     * 测试Lucene删除索引
     * */
    @Test
    public void deleteIndex() throws IOException {
        //创建indexWriter
        Directory directory = FSDirectory.open(new File("f:/index"));
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_44, new IKAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory, conf);

        indexWriter.deleteDocuments(new Term("id", "1"));
        //提交并关闭indexWriter
        indexWriter.commit();
        indexWriter.close();
    }

    @Test
    public void luceneUtilCreateIndex() throws IOException {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();

        //添加document
        try {

            for (int i = 0; i < 10; i++) {

                Document document = new Document();
                document.add(new StringField("id", i + "", Field.Store.YES));
                document.add(new StringField("name", "背影", Field.Store.YES));
                document.add(new StringField("author", "朱自清", Field.Store.YES));
                document.add(new StringField("date", "2015-3-2", Field.Store.YES));
                indexWriter.addDocument(document);
            }

            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }
    }


    public void searchIndex(Query query){
//        int pageSize = 5;
//        int pageNum = 2;
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();

        /*Term term = new Term("title", "同居");
        Query query = new TermQuery(term);*/
        TopDocs topDocs = null;
        try {
            topDocs = indexSearcher.search(query, 100);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (int i = 0; i < scoreDocs.length; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                int docID = scoreDoc.doc;
                Document document = indexSearcher.doc(docID);
                System.out.println(document.get("id"));
                System.out.println(document.get("title"));
                System.out.println(document.get("content"));
                System.out.println(document.get("author"));
                System.out.println(document.get("date"));
                System.out.println("==========================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateIndex() {
        IndexWriter indexWriter = null;
        try {
            Directory dir = FSDirectory.open(new File("f:/index"));
            IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_44, new IKAnalyzer());
            indexWriter = new IndexWriter(dir, conf);
            for (int i = 0; i < 10; i++) {
                Document document = new Document();
                document.add(new IntField("id", i, Field.Store.YES));
                document.add(new TextField("title", "和郝帅同居的日子", Field.Store.YES));
                TextField textField = new TextField("content", "有钱真的可以为所欲为吗?是的喜欢你", Field.Store.YES);
                //textField.setBoost(10f);
                document.add(textField);
                document.add(new StringField("author", "李白", Field.Store.YES));
                document.add(new StringField("date", "2018-5-2", Field.Store.YES));
                indexWriter.addDocument(document);
            }
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException e) {
            try {
                indexWriter.rollback();
                indexWriter.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Test
    public void queryParser() throws ParseException {
        String[] fields = {"content", "title"};
        //1.版本号  2.字段数组 基于那些字段去查询  3.分词器 和创建索引的分词器保持一致
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(Version.LUCENE_44, fields, new IKAnalyzer());
        Query query = multiFieldQueryParser.parse("李白不喜欢,任飞喜欢一个人住");
        searchIndex(query);
    }

    @Test
    public void testMatchAllDocsQuery() {
        Query query = new MatchAllDocsQuery();
        searchIndex(query);
    }

    @Test
    public void testNumericRangeQuery() {
        //应用场景  根据商品价格进行搜索
        //参数一:根据那个字段查询 参数2:最小值 参数三:最大值 参数四:是否包含最小值  参数5:是否包含最大值
        Query query = NumericRangeQuery.newIntRange("id", 3, 6, true, true);
        searchIndex(query);
    }

    @Test
    /*
     * ?代替一个字符
     * *代表多个字符
     * */
    public void testWildcardQuery() {
        Query wildcardQuery = new WildcardQuery(new Term("content", "为?欲为"));
        searchIndex(wildcardQuery);
    }

    @Test
    public void testFuzzyQuery() {
        //maxEdits 错误范围  最大只能为2  再大报错
        FuzzyQuery query = new FuzzyQuery(new Term("content", "为啊打的"), 2);
        searchIndex(query);
    }

    @Test
    public void testBooleanQuery() {
        BooleanQuery query = new BooleanQuery();
        Query numericRangeQuery1 = NumericRangeQuery.newIntRange("id", 1, 6, true, true);
        Query numericRangeQuery2 = NumericRangeQuery.newIntRange("id", 3, 10, true, true);
        //如果存在 should  must  must_not
        query.add(numericRangeQuery1, BooleanClause.Occur.MUST_NOT);
        query.add(numericRangeQuery2, BooleanClause.Occur.MUST);
        searchIndex(query);
    }
}
