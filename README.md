peyote
======

#About
Cacti graph template generator
Built using 10% own project time @Last.fm.
For a lot of our services we monitor their behaviour using cacti (http://www.cacti.net/). Cacti does a great job but it is a bit time-consuming using its webinterface to create data/graph templates. This project tries to simplify that by generating the necessary templates for you. It's built on the use case we have at last.fm.

In short it takes a url (as used in cacti's data input method) and generates the necessary templates that can be imported in cacti. 
It consists of object-xml mapping using JAXB, that I reversed engineered from looking at exported cacti templates and the php code of cacti. It is not guaranteed to work on your installation and could overwrite existing templates. So don't use it on production cacti installation if you are not sure what you are doing. It should be alright though, I tried to make sure the cacti hashkeys that are generated by peyote won't clash with cacti's hashkeys. But: IT COMES WITH NO GUARANTEES, USE AT YOUR OWN RISK.
All of the work was based on cacti version 0.8.7g.

#Building
This project uses the [Maven](http://maven.apache.org/) build system.

Build:
        mvn assembly:single

Run:
        java -jar target/peyote-1.0-SNAPSHOT-jar-with-dependencies.jar	

# Contributing
All contributions are welcome. Please use the [Last.fm codeformatting profile](	) found in the `lastfm-oss-config` project for formatting your changes.

#Legal
Copyright 2013 [Last.fm](http://www.last.fm/)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
 
[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)
 
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
