package com.zuplyx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.fory.Fory;
import org.apache.fory.config.Language;

import schemacrawler.schema.Catalog;
import schemacrawler.schemacrawler.SchemaCrawlerOptionsBuilder;
import schemacrawler.tools.utility.SchemaCrawlerUtility;
import us.fatehi.utility.datasource.DatabaseConnectionSources;

/**
 * Simple test class for a bug in Fory.
 */
public class BugTest
{

  public static void main(String[] args) throws Exception
  {
    BugTest test = new BugTest();
    Connection conn = test.createSchema();
    Catalog catalog = test.crawlSchema(conn);
    test.serializeCatalog(catalog);
  }

  /**
   * Main serialization
   *
   * @param catalog
   */
  private void serializeCatalog(final Catalog catalog)
  {
    Fory fory = Fory
        .builder()
        .withLanguage(Language.JAVA)
        .withMaxDepth(250)
        .requireClassRegistration(false)
        .withRefTracking(true)
        .build();
    fory.serialize(catalog);
  }

  /**
   * Crawl the Schema
   *
   * @param con
   * @return
   */
  private Catalog crawlSchema(Connection con)
  {
    return SchemaCrawlerUtility.getCatalog(
        DatabaseConnectionSources.fromConnection(con),
        SchemaCrawlerOptionsBuilder.newSchemaCrawlerOptions());
  }

  /**
   * Create connection and populate schema
   *
   * @return
   * @throws SQLException
   * @throws IOException
   */
  private Connection createSchema() throws SQLException, IOException
  {
    Connection con = DriverManager.getConnection(
        "jdbc:derby:memory:db;create=true");
    try (Statement st = con.createStatement())
    {
      try (InputStream in = getClass().getResourceAsStream("/sql/init.sql");
          BufferedReader reader = new BufferedReader(
              new InputStreamReader(in, StandardCharsets.UTF_8)))
      {
        for (String line : reader.lines().toList())
        {
          st.execute(line);
        }
      }
    }
    return con;
  }
}
