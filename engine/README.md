# ElementEngine
 A java graphical engine

To install this engine:

```bash
mvn install
```

To use this engine:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>Test2</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <java.version>1.13</java.version>
        <main.class>Main</main.class>
        <assets.path>assets/</assets.path>
        <engine.assets.path>${assets.path}/engine-assets/</engine.assets.path>
        <application.name>DemoApp</application.name>
        <enable.logs>true</enable.logs>
    </properties>

    <dependencies>
        <dependency>
            <groupId>com.elemengine</groupId>
            <artifactId>ElementEngine</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

    <build>
        <sourceDirectory>src/main/java</sourceDirectory>
        <resources>
            <resource>
                <directory>${assets.path}</directory>
                <excludes>
                    <exclude>*/**</exclude>
                </excludes>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>13</source>
                    <target>13</target>
                </configuration>
            </plugin>
            <plugin>
                <artifactId>maven-assembly-plugin</artifactId>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>${main.class}</mainClass>
                        </manifest>
                    </archive>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                </configuration>
                <executions>
                    <execution>
                        <id>make-assembly</id> <!-- this is used for inheritance merges -->
                        <phase>package</phase> <!-- bind to the packaging phase -->
                        <goals>
                            <goal>single</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>${project.build.directory}/${application.name}</outputDirectory>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <artifactId>maven-resources-plugin</artifactId>
                <executions>
                    <execution>
                        <id>copy-resources</id>
                        <phase>package</phase>
                        <goals>
                            <goal>copy-resources</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>${project.build.directory}/${application.name}/${assets.path}</outputDirectory>
                            <resources>
                                <resource>
                                    <directory>${project.basedir}/${assets.path}</directory>
                                    <includes>
                                        <include>*/**</include>
                                    </includes>
                                </resource>
                            </resources>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>properties-maven-plugin</artifactId>
                <version>1.0.0</version>
                <executions>
                    <execution>
                        <phase>generate-resources</phase>
                        <goals>
                            <goal>write-project-properties</goal>
                        </goals>
                        <configuration>
                            <outputFile>${project.build.outputDirectory}/conf.properties</outputFile>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <version>3.2.0</version>
                <executions>
                    <execution>
                        <id>unpack</id>
                        <phase>generate-resources</phase>
                        <goals>
                            <goal>unpack</goal>
                        </goals>
                        <configuration>
                            <artifactItems>
                                <artifactItem>
                                    <groupId>com.elemengine</groupId>
                                    <artifactId>ElementEngine</artifactId>
                                    <version>1.0-SNAPSHOT</version>
                                    <type>jar</type>
                                    <outputDirectory>${assets.path}</outputDirectory>
                                    <includes>engine-assets/**</includes>
                                </artifactItem>
                            </artifactItems>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>

```

Run project in developpement:

```bash
mvn clean compile -P dev
java path/to/main/class
```

Package application:

```bash
mvn clean package -P release
```

