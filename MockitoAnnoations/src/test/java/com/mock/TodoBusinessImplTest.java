package com.mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnit44Runner;

@RunWith(MockitoJUnit44Runner.class)
public class TodoBusinessImplTest {

	@Mock
	TodoService todoservice;
	
	@InjectMocks
	TodoBusinessImpl impl;
	
	@Captor
	ArgumentCaptor<String> argumentCaptor;

	
	@Test
	public void testRetrieveTodosRelatedToSpring() {
		List<String> list=Arrays.asList("spring java","spring boot","learn dance");
		when(todoservice.retrieveTodos("dummy")).thenReturn(list);
		List<String> fliteredList=impl.retrieveTodosRelatedToSpring("dummy");
		
		assertEquals(2, fliteredList.size());
		
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_withEmptyList() {
		List<String> fliteredList=impl.retrieveTodosRelatedToSpring("dummy");
		
		assertEquals(0, fliteredList.size());
		
	}

	@Test
	public void testDeleteTodosNotRelatedToSpring() {
		List<String> list=Arrays.asList("spring java","spring boot","learn dance");
		when(todoservice.retrieveTodos("dummy")).thenReturn(list);
		impl.deleteTodosNotRelatedToSpring("dummy");
		
          verify(todoservice).deleteTodo("learn dance");
          verify(todoservice,Mockito.never()).deleteTodo("spring java");
          verify(todoservice,Mockito.times(1)).deleteTodo("learn dance");
	}
	
	@Test
	public void captureArgument() {
		List<String> list=Arrays.asList("spring java","spring boot","learn dance");
		when(todoservice.retrieveTodos("dummy")).thenReturn(list);
		impl.deleteTodosNotRelatedToSpring("dummy");
		
        verify(todoservice).deleteTodo(argumentCaptor.capture());
        
        assertEquals("learn dance", argumentCaptor.getValue());

		
	}
}
