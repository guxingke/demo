package com.gxk.demo.orm;

import com.gxk.demo.orm.criteria.GeneratorImpl;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import static com.gxk.demo.orm.criteria.Criteria.*;
import static org.junit.jupiter.api.Assertions.*;

class TemplateTest {

  static HikariDataSource hds;

  DatabaseTemplate db;
  DataSource ds;
  JdbcTemplate jdbcTemplate;

  @BeforeAll
  static void beforeAll() {
    hds = new HikariDataSource();
    hds.setDriverClassName("org.h2.Driver");
    hds.setJdbcUrl("jdbc:h2:mem:test;MODE=MYSQL;");
    hds.setUsername("sa");
    hds.setPassword("sa");

    // init tables
    try (Connection conn = hds.getConnection()) {
      var sts = conn.createStatement();
      sts.execute("create table users(id int primary key, name varchar(20), nick varchar(20), createdAt timestamp)");
      sts.execute("create table books(id int primary key auto_increment, name varchar(20), createdAt timestamp)");
      sts.execute("insert into users values(1, 'test', 'vvv', '2022-12-02 11:11:11') ");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @AfterAll
  static void afterAll() {
    hds.close();
  }

  @BeforeEach
  void setUp() {
    ds = hds;
    jdbcTemplate = new JdbcTemplate(ds);
    db = new DatabaseTemplate(new GeneratorImpl(), jdbcTemplate);
  }

  @Test
  void test_ds() throws SQLException {
    var con = ds.getConnection();
    Assertions.assertNotNull(con);

    var rs = con.createStatement()
        .executeQuery("select count(*) from users");
    Assertions.assertTrue(rs.next());

    var v = jdbcTemplate.queryForObject("select count(*) from users", Long.class);
    assertTrue(v != null);
  }

  @Test
  void test_list() {
    var users = db.from(User.class)
        .projects("*")
        .where("name = ?", "test")
        .skip(0)
        .limit(10)
        .list();
    assertEquals(1, users.size());
  }

  @Test
  void test_count() {
    var count = db.from(User.class)
        .count();
    assertTrue(count > 0);

    count = db.from(User.class)
        .where("name = ?", "test")
        .count();
    assertEquals(1, count);

    count = db.from(User.class)
        .where(column("name").is("test"))
        .count();
    assertEquals(1, count);

    count = db.from(User.class)
        .where(column("id").is(1))
        .count();
    assertEquals(1, count);

    // ---
    count = db.from(User.class)
        .where(
            or(
                column("name").is("test-2"),
                and(
                    column("id").is(1),
                    column("nick").is("vvv")
                )
            )
        ).count();

    assertEquals(1, count);
  }

  @Test
  public void test_range() {
    var one = 1000L * 3600 * 24;
    var user = db.from(User.class)
        .where(
            column("createdAt")
                .range(new Date(1669882287650L), new Date(1670832699489L))
        )
        .first();
    assertTrue(user.isPresent());
  }

  @Test
  void test_one() {
    var user = db.from(User.class)
        .first();
    assertTrue(user.isPresent());

    var u = user.get();
    assertEquals(1, u.getId());
    assertEquals("test", u.getName());
    assertEquals("vvv", u.getNick());
    assertNotNull(u.getCreatedAt());

    user = db.from(User.class)
        .where("id = ?", 1)
        .first();
    assertTrue(user.isPresent());
  }

  @Test
  void test_exists() {
    var ex = db.from(User.class)
        .where("id = ?", 1)
        .exists();
    assertTrue(ex);
  }

  @Test
  void test_criteria() {
    var user = db.from(User.class)
        .where(
            or(
                column("name").is("v"),
                and(
                    column("name").is("test"),
                    column("nick").notNull()
                )
            ))
        .first();

    assertTrue(user.isPresent());
  }

  @Test
  void test_generator() {
    var criteria = or(
        column("name").is("v"),
        and(
            column("name").is("test"),
            column("nick").isNull(),
            column("city").notNull()
        )
    );

    var p = new GeneratorImpl().gen(criteria);
    assertEquals("(name = ? OR (name = ? AND nick IS NULL AND city IS NOT NULL))", p.left());
    assertEquals("v", p.right()[0]);
    assertEquals("test", p.right()[1]);
  }

  @Test
  void test_save_update() {
    var now = System.currentTimeMillis();

    var u = new User(1, "test", "vvv", new Date(now));
    var r = db.save(u);
    assertTrue(r);

    var u2 = db.findById(1, User.class).get();
    assertTrue(now == u2.getCreatedAt().getTime());
  }

  @Test
  void test_save_insert() {
    var u = new User(10, "t2", "vvv", new Date());
    var r = db.save(u);
    assertTrue(r);
  }

  @Test
  void test_save_insert_generated_key() {
    var b = new Book(null, "test", new Date());
    var r = db.save(b);
    assertTrue(r);

    assertTrue(b.getId() > 0);
  }

  @Test
  public void test_bind() {
    var users = db
        .createQuery("select * from users where id = ?", new Object[]{1})
        .map(User.class)
        .list();

    assertEquals(1, users.size());
  }
}
