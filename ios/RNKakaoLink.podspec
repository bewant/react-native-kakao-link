
Pod::Spec.new do |s|
  s.name         = "RNKakaoLink"
  s.version      = "1.0.0"
  s.summary      = "RNKakaoLink"
  s.description  = <<-DESC
                  RNKakaoLink
                   DESC
  s.homepage     = "https://reflash.kr"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "kimkr" => "kimkr@reflash.kr" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/kimkr/RNKakaoLink.git", :tag => "master" }
  s.source_files  = "RNKakaoLink/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  
