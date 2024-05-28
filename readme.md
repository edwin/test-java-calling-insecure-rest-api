# Connecting to a self sign https URL

A single java class for testing connectivity to a web with a self-signed https protocol. 

## Exception Logs
This is what happen without using a truststore
```
Caused by: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
	at java.base/sun.security.validator.PKIXValidator.doBuild(PKIXValidator.java:439)
	at java.base/sun.security.validator.PKIXValidator.engineValidate(PKIXValidator.java:306)
	at java.base/sun.security.validator.Validator.validate(Validator.java:264)
	at java.base/sun.security.ssl.X509TrustManagerImpl.checkTrusted(X509TrustManagerImpl.java:231)
	at java.base/sun.security.ssl.X509TrustManagerImpl.checkServerTrusted(X509TrustManagerImpl.java:132)
	at java.base/sun.security.ssl.CertificateMessage$T13CertificateConsumer.checkServerCerts(CertificateMessage.java:1341)
	... 18 more
Caused by: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
	at java.base/sun.security.provider.certpath.SunCertPathBuilder.build(SunCertPathBuilder.java:141)
	at java.base/sun.security.provider.certpath.SunCertPathBuilder.engineBuild(SunCertPathBuilder.java:126)
	at java.base/java.security.cert.CertPathBuilder.build(CertPathBuilder.java:297)
	at java.base/sun.security.validator.PKIXValidator.doBuild(PKIXValidator.java:434)
	... 23 more
```

## Generate Trust Store
```
$ echo "" | openssl s_client -connect some-insecure-url:443  -showcerts 2>/dev/null | openssl x509 -out my.cert

$ keytool -import -alias ca -file my.cert -keystore customcacerts  -storepass changeit
```

## Run File with Trust Store
```
$ java -Djavax.net.ssl.trustStore=customcacerts -Djavax.net.ssl.trustStorePassword=changeit com.edw.Main
```