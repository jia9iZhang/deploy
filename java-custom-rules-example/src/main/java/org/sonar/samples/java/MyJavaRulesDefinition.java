/*
 * Copyright (C) 2012-2022 SonarSource SA - mailto:info AT sonarsource DOT com
 * This code is released under [MIT No Attribution](https://opensource.org/licenses/MIT-0) license.
 */
package org.sonar.samples.java;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionAnnotationLoader;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;
import org.sonar.plugins.java.Java;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Declare rule metadata in server repository of rules.
 * That allows to list the rules in the page "Rules".
 */
public class MyJavaRulesDefinition implements RulesDefinition {

  // don't change that because the path is hard coded in CheckVerifier
  private static final String RESOURCE_BASE_PATH = "/example/foolint-rules.xml";

  public static final String REPOSITORY_KEY = "mycompany-java";
  public static final String REPOSITORY_NAME = "MyCompany Custom Repository";

  // Add the rule keys of the rules which need to be considered as template-rules
  private static final Set<String> RULE_TEMPLATES_KEY = Collections.emptySet();


  @Override
  public void define(Context context) {
    NewRepository repository = context.createRepository(REPOSITORY_KEY, Java.KEY).setName(REPOSITORY_NAME);
    InputStream rulesXml = MyJavaRulesDefinition.class.getResourceAsStream(RESOURCE_BASE_PATH);
    if (rulesXml != null) {
      RulesDefinitionXmlLoader xmlLoader = new RulesDefinitionXmlLoader();
      xmlLoader.load(repository, rulesXml, StandardCharsets.UTF_8.name());
    }
    repository.done();
  }

//  private static void setTemplates(NewRepository repository) {
//    RULE_TEMPLATES_KEY.stream()
//      .map(repository::rule)
//      .filter(Objects::nonNull)
//      .forEach(rule -> rule.setTemplate(true));
//  }
}
