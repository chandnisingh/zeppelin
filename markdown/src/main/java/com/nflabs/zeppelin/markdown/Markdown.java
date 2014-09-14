package com.nflabs.zeppelin.markdown;

import java.io.IOException;
import java.util.Properties;

import org.markdown4j.Markdown4jProcessor;

import com.nflabs.zeppelin.interpreter.Interpreter;
import com.nflabs.zeppelin.interpreter.InterpreterResult;
import com.nflabs.zeppelin.interpreter.InterpreterResult.Code;

public class Markdown extends Interpreter {
	private Markdown4jProcessor md;

	static {
		Interpreter.register("md", Markdown.class.getName());
	}
	
	public Markdown(Properties property){
		super(property);
	}	
	
	@Override
	public void open() {
		md = new Markdown4jProcessor();
	}

	@Override
	public void close() {
	}

	@Override
	public Object getValue(String name) {
		return null;
	}

	@Override
	public InterpreterResult interpret(String st) {
		String html;
		try {
			html = md.process(st);
		} catch (IOException e) {
			return new InterpreterResult(Code.ERROR, e.getMessage());
		}
		return new InterpreterResult(Code.SUCCESS, "%html "+html);
	}

	@Override
	public void cancel() {
	}

	@Override
	public void bindValue(String name, Object o) {
	}

	@Override
	public FormType getFormType() {
		return FormType.SIMPLE;
	}

	@Override
	public int getProgress() {
		return 0;
	}
}
