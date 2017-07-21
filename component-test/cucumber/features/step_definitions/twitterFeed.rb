require 'net/http'
require 'uri'
require 'openssl'
require 'keystores'

def load_message(fixture_file)
  result = ModavCucumber.load_fixture("#{fixture_file}.json")
  JSON.parse(result)
end

def restclient_ssl_connect()

  # Load can take any IO object, or a path to a file
  # key_store_password = 'Aelliott1963'
  # keystore = OpenSSL::JKS.new
  # keystore.load('/Library/Java/JavaVirtualMachines/jdk1.8.0_92.jdk/Contents/Home/jre/lib/security/keystore.jks', key_store_password)
  # certificate = keystore.get_certificate('twitter2')
  # key = keystore.get_key('twitter2', key_store_password)
  # puts certificate
  # puts key
  # puts "******************************************"
  # puts certificate.check_private_key(key)


  myResource = RestClient::Resource.new(
      'https://localhost:8443/hello',
      # :ssl_ca_file => Gem.configuration.ssl_ca_cert,
      # :ssl_ca_file => '/usr/local/etc/openssl/cert.pem',
      # :ssl_client_cert  =>  certificate,
      # :ssl_client_key   =>  key,
      :verify_ssl       =>    false,
      # :verify_ssl       =>  OpenSSL::SSL::VERIFY_PEER,
  )

  # puts OpenSSL::SSL::VERIFY_PEER
  result = myResource.get
  @responseJSON = JSON.parse(result.body)

end

# restclient_ssl_connect("https://odsr.int.api.bbci.co.uk/v1/rules/pips-pid-#{@vpid}/none").put(@rules.to_s, headers)

def curlhello
  uri = URI.parse("https://localhost:8443/hello")
  request = Net::HTTP::Post.new(uri)
  req_options = {
      use_ssl: uri.scheme == "https",
  }
  puts uri.hostname
  puts uri.port
  response_text = Net::HTTP.start(uri.hostname, uri.port, req_options) do |http|
    http.request(request)
  end
  @responseJSON = JSON.parse(response_text.body)
end


When(/^url called$/) do
  # curlhello
  restclient_ssl_connect
end

Then(/^Lambda will respond with a JSON like (.*)$/) do |fixture_name|
  @expected_result = load_message(fixture_name)
  puts @responseJSON[0]['user']['description']
  puts @expected_result['user']['description']
  expect(@responseJSON[0]['user']['description']).to eq(@expected_result['user']['description'])
end
