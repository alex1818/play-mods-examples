/**
 * Copyright 2015 Thomas Feng
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package beans;

import org.apache.spark.SparkConf;
import org.apache.spark.deploy.master.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import akka.actor.ActorSystem;
import me.tfeng.toolbox.spring.Startable;

/**
 * @author Thomas Feng (huining.feng@gmail.com)
 */
@Component("sparkMasterStartable")
public class SparkMasterStartable implements Startable {

  private ActorSystem actorSystem;

  @Autowired
  private SparkConf sparkConf;

  @Value("${spark.host}")
  private String sparkHost;

  @Value("${spark.master.port}")
  private int sparkMasterPort;

  @Value("${spark.master.webui.port}")
  private int sparkMasterWebUIPort;

  @Override
  public void onStart() throws Throwable {
    actorSystem = Master.startSystemAndActor(sparkHost, sparkMasterPort, sparkMasterWebUIPort, sparkConf)._1();
  }

  @Override
  public void onStop() throws Throwable {
    actorSystem.shutdown();
  }
}
