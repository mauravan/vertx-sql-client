package io.reactiverse.pgclient.codec.extended;

import io.reactiverse.pgclient.PgClient;
import io.reactiverse.pgclient.Row;
import io.reactiverse.pgclient.Tuple;
import io.reactiverse.pgclient.codec.ColumnChecker;
import io.reactiverse.pgclient.codec.ExtendedQueryDataTypeCodecTestBase;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;

public class CharacterTypesTest extends ExtendedQueryDataTypeCodecTestBase {
  @Test
  public void testDecodeName(TestContext ctx) {
    Async async = ctx.async();
    PgClient.connect(vertx, options, ctx.asyncAssertSuccess(conn -> {
      conn.prepare("SELECT \"Name\" FROM \"CharacterDataType\" WHERE \"id\" = $1",
        ctx.asyncAssertSuccess(p -> {
          p.execute(Tuple.tuple().addInteger(1), ctx.asyncAssertSuccess(result -> {
            ctx.assertEquals(1, result.size());
            ctx.assertEquals(1, result.rowCount());
            Row row = result.iterator().next();
            String name = "What is my name ?";
            ColumnChecker.checkColumn(0, "Name")
              .returns(Tuple::getValue, Row::getValue, name)
              .returns(Tuple::getString, Row::getString, name)
              .forRow(row);
            async.complete();
          }));
        }));
    }));
  }

  @Test
  public void testEncodeName(TestContext ctx) {
    Async async = ctx.async();
    PgClient.connect(vertx, options, ctx.asyncAssertSuccess(conn -> {
      conn.prepare("UPDATE \"CharacterDataType\" SET \"Name\" = upper($1) WHERE \"id\" = $2 RETURNING \"Name\"",
        ctx.asyncAssertSuccess(p -> {
          p.execute(Tuple.tuple()
            .addString("vert.x")
            .addInteger(2), ctx.asyncAssertSuccess(result -> {
            ctx.assertEquals(1, result.size());
            ctx.assertEquals(1, result.rowCount());
            Row row = result.iterator().next();
            String name = "VERT.X";
            ColumnChecker.checkColumn(0, "Name")
              .returns(Tuple::getValue, Row::getValue, name)
              .returns(Tuple::getString, Row::getString, name)
              .forRow(row);
            async.complete();
          }));
        }));
    }));
  }


  @Test
  public void testDecodeChar(TestContext ctx) {
    Async async = ctx.async();
    PgClient.connect(vertx, options, ctx.asyncAssertSuccess(conn -> {
      conn.prepare("SELECT \"SingleChar\" FROM \"CharacterDataType\" WHERE \"id\" = $1",
        ctx.asyncAssertSuccess(p -> {
          p.execute(Tuple.tuple().addInteger(1), ctx.asyncAssertSuccess(result -> {
            ctx.assertEquals(1, result.size());
            ctx.assertEquals(1, result.rowCount());
            Row row = result.iterator().next();
            String singleChar = "A";
            ColumnChecker.checkColumn(0, "SingleChar")
              .returns(Tuple::getValue, Row::getValue, singleChar)
              .returns(Tuple::getString, Row::getString, singleChar)
              .forRow(row);
            async.complete();
          }));
        }));
    }));
  }

  @Test
  public void testEncodeChar(TestContext ctx) {
    Async async = ctx.async();
    PgClient.connect(vertx, options, ctx.asyncAssertSuccess(conn -> {
      conn.prepare("UPDATE \"CharacterDataType\" SET \"SingleChar\" = upper($1) WHERE \"id\" = $2 RETURNING \"SingleChar\"",
        ctx.asyncAssertSuccess(p -> {
          p.execute(Tuple.tuple()
            .addString("b")
            .addInteger(2), ctx.asyncAssertSuccess(result -> {
            ctx.assertEquals(1, result.size());
            ctx.assertEquals(1, result.rowCount());
            Row row = result.iterator().next();
            String singleChar = "B";
            ColumnChecker.checkColumn(0, "SingleChar")
              .returns(Tuple::getValue, Row::getValue, singleChar)
              .returns(Tuple::getString, Row::getString, singleChar)
              .forRow(row);
            async.complete();
          }));
        }));
    }));
  }

  @Test
  public void testDecodeFixedChar(TestContext ctx) {
    Async async = ctx.async();
    PgClient.connect(vertx, options, ctx.asyncAssertSuccess(conn -> {
      conn.prepare("SELECT \"FixedChar\" FROM \"CharacterDataType\" WHERE \"id\" = $1",
        ctx.asyncAssertSuccess(p -> {
          p.execute(Tuple.tuple().addInteger(1), ctx.asyncAssertSuccess(result -> {
            ctx.assertEquals(1, result.size());
            ctx.assertEquals(1, result.rowCount());
            Row row = result.iterator().next();
            String name = "YES";
            ColumnChecker.checkColumn(0, "FixedChar")
              .returns(Tuple::getValue, Row::getValue, name)
              .returns(Tuple::getString, Row::getString, name)
              .forRow(row);
            async.complete();
          }));
        }));
    }));
  }

  @Test
  public void testEncodeFixedChar(TestContext ctx) {
    Async async = ctx.async();
    PgClient.connect(vertx, options, ctx.asyncAssertSuccess(conn -> {
      conn.prepare("UPDATE \"CharacterDataType\" SET \"FixedChar\" = upper($1) WHERE \"id\" = $2 RETURNING \"FixedChar\"",
        ctx.asyncAssertSuccess(p -> {
          p.execute(Tuple.tuple()
            .addString("no")
            .addInteger(2), ctx.asyncAssertSuccess(result -> {
            ctx.assertEquals(1, result.size());
            ctx.assertEquals(1, result.rowCount());
            Row row = result.iterator().next();
            String name = "NO ";
            ColumnChecker.checkColumn(0, "FixedChar")
              .returns(Tuple::getValue, Row::getValue, name)
              .returns(Tuple::getString, Row::getString, name)
              .forRow(row);
            async.complete();
          }));
        }));
    }));
  }

  @Test
  public void testDecodeText(TestContext ctx) {
    Async async = ctx.async();
    PgClient.connect(vertx, options, ctx.asyncAssertSuccess(conn -> {
      conn.prepare("SELECT \"Text\" FROM \"CharacterDataType\" WHERE \"id\" = $1",
        ctx.asyncAssertSuccess(p -> {
          p.execute(Tuple.tuple().addInteger(1), ctx.asyncAssertSuccess(result -> {
            ctx.assertEquals(1, result.size());
            ctx.assertEquals(1, result.rowCount());
            Row row = result.iterator().next();
            String name = "Hello World";
            ColumnChecker.checkColumn(0, "Text")
              .returns(Tuple::getValue, Row::getValue, name)
              .returns(Tuple::getString, Row::getString, name)
              .forRow(row);
            async.complete();
          }));
        }));
    }));
  }

  @Test
  public void testEncodeText(TestContext ctx) {
    Async async = ctx.async();
    PgClient.connect(vertx, options, ctx.asyncAssertSuccess(conn -> {
      conn.prepare("UPDATE \"CharacterDataType\" SET \"Text\" = upper($1) WHERE \"id\" = $2 RETURNING \"Text\"",
        ctx.asyncAssertSuccess(p -> {
          p.execute(Tuple.tuple()
            .addString("Hello World")
            .addInteger(2), ctx.asyncAssertSuccess(result -> {
            ctx.assertEquals(1, result.size());
            ctx.assertEquals(1, result.rowCount());
            Row row = result.iterator().next();
            String name = "HELLO WORLD";
            ColumnChecker.checkColumn(0, "Text")
              .returns(Tuple::getValue, Row::getValue, name)
              .returns(Tuple::getString, Row::getString, name)
              .forRow(row);
            async.complete();
          }));
        }));
    }));
  }

  @Test
  public void testDecodeVarCharacter(TestContext ctx) {
    Async async = ctx.async();
    PgClient.connect(vertx, options, ctx.asyncAssertSuccess(conn -> {
      conn.prepare("SELECT \"VarCharacter\" FROM \"CharacterDataType\" WHERE \"id\" = $1",
        ctx.asyncAssertSuccess(p -> {
          p.execute(Tuple.tuple().addInteger(1), ctx.asyncAssertSuccess(result -> {
            ctx.assertEquals(1, result.size());
            ctx.assertEquals(1, result.rowCount());
            Row row = result.iterator().next();
            String name = "Great!";
            ColumnChecker.checkColumn(0, "VarCharacter")
              .returns(Tuple::getValue, Row::getValue, name)
              .returns(Tuple::getString, Row::getString, name)
              .forRow(row);
            async.complete();
          }));
        }));
    }));
  }

  @Test
  public void testEncodeVarCharacter(TestContext ctx) {
    Async async = ctx.async();
    PgClient.connect(vertx, options, ctx.asyncAssertSuccess(conn -> {
      conn.prepare("UPDATE \"CharacterDataType\" SET \"VarCharacter\" = upper($1) WHERE \"id\" = $2 RETURNING \"VarCharacter\"",
        ctx.asyncAssertSuccess(p -> {
          p.execute(Tuple.tuple()
            .addString("Great!")
            .addInteger(2), ctx.asyncAssertSuccess(result -> {
            ctx.assertEquals(1, result.size());
            ctx.assertEquals(1, result.rowCount());
            Row row = result.iterator().next();
            String name = "GREAT!";
            ColumnChecker.checkColumn(0, "VarCharacter")
              .returns(Tuple::getValue, Row::getValue, name)
              .returns(Tuple::getString, Row::getString, name)
              .forRow(row);
            async.complete();
          }));
        }));
    }));
  }

  @Test
  public void testEncodeLargeVarchar(TestContext ctx) {
    int len = 2048;
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < len; i++) {
      builder.append((char) ('A' + (i % 26)));
    }
    String value = builder.toString();
    Async async = ctx.async();
    PgClient.connect(vertx, options, ctx.asyncAssertSuccess(conn -> {
      conn.prepare("SELECT $1::VARCHAR(" + len + ")",
        ctx.asyncAssertSuccess(p -> {
          p.execute(Tuple.of(value), ctx.asyncAssertSuccess(result -> {
            ctx.assertEquals(value, result.iterator().next().getString(0));
            async.complete();
          }));
        }));
    }));
  }

  @Test
  public void testDecodeStringArray(TestContext ctx) {
    Async async = ctx.async();
    PgClient.connect(vertx, options, ctx.asyncAssertSuccess(conn -> {
      conn.prepare("SELECT \"Text\" FROM \"ArrayDataType\" WHERE \"id\" = $1",
        ctx.asyncAssertSuccess(p -> {
          p.execute(Tuple.tuple()
            .addInteger(1), ctx.asyncAssertSuccess(result -> {
            ColumnChecker.checkColumn(0, "Text")
              .returns(Tuple::getValue, Row::getValue, new String[]{"Knock, knock.Who’s there?very long pause….Java."})
              .returns(Tuple::getStringArray, Row::getStringArray, new String[]{"Knock, knock.Who’s there?very long pause….Java."})
              .forRow(result.iterator().next());
            async.complete();
          }));
        }));
    }));
  }

  @Test
  public void testEncodeStringArray(TestContext ctx) {
    Async async = ctx.async();
    PgClient.connect(vertx, options, ctx.asyncAssertSuccess(conn -> {
      conn.prepare("UPDATE \"ArrayDataType\" SET \"Text\" = $1  WHERE \"id\" = $2 RETURNING \"Text\"",
        ctx.asyncAssertSuccess(p -> {
          p.execute(Tuple.tuple()
              .addStringArray(new String[]{"Knock, knock.Who’s there?"})
              .addInteger(2)
            , ctx.asyncAssertSuccess(result -> {
              ColumnChecker.checkColumn(0, "Text")
                .returns(Tuple::getValue, Row::getValue, new String[]{"Knock, knock.Who’s there?"})
                .returns(Tuple::getStringArray, Row::getStringArray, new String[]{"Knock, knock.Who’s there?"})
                .forRow(result.iterator().next());
              async.complete();
            }));
        }));
    }));
  }
}
