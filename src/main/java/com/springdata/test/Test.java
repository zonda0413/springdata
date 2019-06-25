package com.springdata.test;

import com.springdata.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Test {
    public static MongoTemplate mongoTemplate;
    static {
        ApplicationContext app = new ClassPathXmlApplicationContext("spring.xml");
        mongoTemplate  = (MongoTemplate) app.getBean("mongoTemplate");

    }

    public static void main(String[] args) {
//        insert();
//        deleteByCondition();
//        deleteReturn();
//        deleteList();
//        deleteCollection();
//        dropDB();
//        update();
        upsert();
    }


    @Deprecated
    /**
     * ssdvss
     * {@link com.springdata.entity.Student}
     * @see com.springdata.entity.Student
     */
    public static void insert(){
        List<Student> list = new ArrayList<Student>();
        for (int i = 5; i < 10; i++) {
            Student student = new Student();
            student.setName("张三" + i).setAge(20 + i).setAddTime(new Date());
            list.add(student);
        }
        //将list存入mongodb中
        mongoTemplate.insert(list, Student.class);
    }

    //删除
    public static void deleteByCondition(){
        mongoTemplate.remove(Query.query(new Criteria("name").is("张三0")), Student.class);
    }

    //删除并返回
    public static void deleteReturn(){
        Student student = mongoTemplate.findAndRemove(Query.query(new Criteria("name").is("张三1")), Student.class);
        System.out.println(student);
    }
    //删除多条数据
    public static void deleteList(){
        List<Student> list = mongoTemplate.findAllAndRemove(Query.query(new Criteria("age").is(20)), Student.class);
        for (Student s : list) {
            System.out.println(s);
        }
    }
    //删除集合
    public static void deleteCollection(){
//        mongoTemplate.dropCollection(Student.class);
        mongoTemplate.dropCollection("student");
    }
    /**
     * 删除数据库
     */
    public static void dropDB(){
        mongoTemplate.getDb().drop();
    }

    /**
     * 修改
     */
    public static void update(){
//        mongoTemplate.updateFirst(Query.query(new Criteria("name").is("张三5")), Update.update("age", 100), Student.class);
        Update update = new Update();
        update.set("age", 200);
        update.set("a_time", new Date());
//        mongoTemplate.updateFirst(Query.query(new Criteria("name").is("张三6")), Update.update("a_time", new Date()).update("age", 102), Student.class);
        mongoTemplate.updateFirst(Query.query(new Criteria("name").is("曹操")), update,Student.class);
    }

    /**
     * 修改，如果没有则添加
     */
    public static void upsert(){
        mongoTemplate.upsert(Query.query(new Criteria("name").is("刘备")), Update.update("age", 300).update("a_time", new Date()), Student.class);
    }

}
