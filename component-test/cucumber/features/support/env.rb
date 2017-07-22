require 'ap'
require 'mod_av_cucumber_env'
require 'mod_av_cucumber/fixtures'
require 'aws-sdk'
require 'rest-client'

AfterConfiguration do
  RA_PORT = 9999
  RestAssured::Server.start(database: ':memory', port: RA_PORT)
  TWITTER_BASE = 'https://api.twitter.com/1.1/'.freeze

  ModavCucumber.setup_and_start_under_test(
    ['../../run.sh'],
  )
end
