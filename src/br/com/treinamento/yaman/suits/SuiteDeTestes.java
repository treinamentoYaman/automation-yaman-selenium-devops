package br.com.treinamento.yaman.suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.treinamento.yaman.tests.Exampl2;
import br.com.treinamento.yaman.tests.Example1;

@RunWith(Suite.class)
@Suite.SuiteClasses({Exampl2.class, Example1.class})
public class SuiteDeTestes {
}

