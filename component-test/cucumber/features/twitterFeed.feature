# Created by ellioe03 at 17/07/2017
Feature: check twitter feed for FreeHackney
  Read and store  FreeHackney twitter feed and then take action

  Scenario: Twitter app responds with JSON when when link called
    When url called
    Then Lambda will respond with a JSON like tweet1