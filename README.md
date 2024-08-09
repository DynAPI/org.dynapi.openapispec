[![Publish Build to Github Releases](https://github.com/DynAPI/org.dynapi.openapispec/actions/workflows/publish-release.yaml/badge.svg)](https://github.com/DynAPI/org.dynapi.openapispec/actions/workflows/publish-release.yaml)
[![Publish package to GitHub Packages](https://github.com/DynAPI/org.dynapi.openapispec/actions/workflows/publish-package.yaml/badge.svg)](https://github.com/DynAPI/org.dynapi.openapispec/actions/workflows/publish-package.yaml)
# org.dynapi.openapispec
OpenAPI Specification Builder Library

```java
package org.dynapi;

import org.dynapi.openapispec.*;
import org.dynapi.openapispec.core.*;
import org.dynapi.openapispec.core.types.*;
import org.json.JSONObject;

public class Example {
    public static void main(String[] args) {
        OpenApiSpecBuilder.Meta meta = OpenApiSpecBuilder.Meta.builder()
                .title("MyAPI")
                .description("My API")
                .version("1.0.0")
                .logo("/assets/logo.png")
                .build();
        OpenApiSpecBuilder spec = new OpenApiSpecBuilder(meta);
        spec.addPath(new Path("/hello/<name>")
                .addMethod("GET", new PathSchema()
                        .addPathParameter(Parameter.builder()
                                .name("name")
                                .description("Person to greet")
                                .schema(new TString())
                                .build()
                        )
                        .addResponse(200, new TString()
                                .example("Hello World")
                        )
                        .addResponse(500, new TObject()
                                .addProperty("error", new TString()
                                        .example("NullPointerException")
                                        .description("Error Group")
                                )
                                .addProperty("detail", new TString()
                                        .description("Detailed information about the error")
                                )
                        )
                )
        );
        JSONObject specification = spec.build();
        System.out.println(specification.toString(2));
    }
}
```
