package com.jmethods.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.testing.LocalDatastoreHelper;

/**
 * Unit test for simple App.
 */
public class AppTest {

	private static LocalDatastoreHelper helper;
	private static Datastore datastore;

	@BeforeClass
	public static void beforeClass() throws IOException, InterruptedException {
		helper = LocalDatastoreHelper.create(1.0);
		helper.start();
		datastore = helper.getOptions().getService();
	}

	@Test
	public void testApp() {
		KeyFactory kf = datastore.newKeyFactory();
		IncompleteKey incompleteKey = kf.setKind("MyEntity").newKey();
		FullEntity<IncompleteKey> fullEntity = FullEntity.newBuilder().setKey(incompleteKey).set("name", "John Doe")
				.build();
		Entity entity = datastore.add(fullEntity);
		Entity entity2 = datastore.get(entity.getKey());
		assertEquals(entity, entity2);
	}

	@Test
	public void testApp2() {
		KeyFactory kf = datastore.newKeyFactory();
		IncompleteKey incompleteKey = kf.setKind("MyEntity").newKey();
		FullEntity<IncompleteKey> fullEntity = FullEntity.newBuilder().setKey(incompleteKey).set("name", "John Doe")
				.build();
		Entity entity = datastore.add(fullEntity);
		Entity entity2 = Entity.newBuilder(entity).set("name", "John Smith").build();
		datastore.update(entity2);
		Entity entity3 = datastore.get(entity2.getKey());
		assertEquals(entity2, entity3);
		assertNotEquals(entity, entity3);
	}

}
