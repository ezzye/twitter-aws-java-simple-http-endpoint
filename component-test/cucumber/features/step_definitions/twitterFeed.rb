require 'net/http'
require 'uri'
require 'openssl'
require 'keystores'

def load_message(fixture_file)
  result = ModavCucumber.load_fixture("#{fixture_file}.json")
  JSON.parse(result)
end

def restclient_ssl_connect()
  myResource = RestClient::Resource.new(
      'https://localhost:8443/hello',
      :verify_ssl       =>    false,
  )
  result = myResource.get
  @responseJSON = JSON.parse(result.body)
end

When(/^url called$/) do
  restclient_ssl_connect
end

Then(/^Lambda will respond with a JSON like (.*)$/) do |fixture_name|
  @expected_result = load_message(fixture_name)
  puts @responseJSON[0]['user']['description']
  puts @expected_result['user']['description']
  expect(@responseJSON[0]['user']['description']).to eq(@expected_result['user']['description'])
end
