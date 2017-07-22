class FixtureFile
attr_reader :fixture, :body, :body_json

  def initialize(fixture_name, prefix, suffix)
    read_file(fixture_name, prefix, suffix)
  end

  def readfile(fixture_name, prefix, suffix)
    @fixture = "#{prefix}/#{fixture_name}_#{suffix}.json"
    @body = ModavCucumber.load_fixture(@fixture)
    @body_json = JSON.parse(@body)
  end
end

class MockedTwitter < FixtureFile
  def initialize(fixture_name)
    super(fixture_name, "Twitter", "Response")
  end


  # Example path = "statuses/user_timeline.json?screen_name=FreeHackney&include_my_retweet=true&count=2&page=1&include_entities=true"
  def mock_twitter(path)
    RestAssured::Double.create(
        fullpath: "#{TWITTER_BASE}/availability/#{path}",
        content: @body
    )
  end

end