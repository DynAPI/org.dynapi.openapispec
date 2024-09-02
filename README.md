[![Publish Build to Github Releases](https://github.com/DynAPI/org.dynapi.openapispec/actions/workflows/publish-release.yaml/badge.svg)](https://github.com/DynAPI/org.dynapi.openapispec/actions/workflows/publish-release.yaml)
[![Publish package to GitHub Packages](https://github.com/DynAPI/org.dynapi.openapispec/actions/workflows/publish-package.yaml/badge.svg)](https://github.com/DynAPI/org.dynapi.openapispec/actions/workflows/publish-package.yaml)
# org.dynapi.openapispec
OpenAPI Specification Builder Library

```java
package org.dynapi;

import org.dynapi.openapispec.*;
import org.dynapi.openapispec.core.*;
import org.dynapi.openapispec.core.schema.*;
import org.json.JSONObject;

public class Example {
    public static void main(String[] args) {
        Info info = Info.builder()
                .title("MyAPI")
                .description("My API")
                .version("1.0.0")
                .build();
        OpenApiSpecBuilder spec = new OpenApiSpecBuilder(info)
                .logo("DynAPI-compressed.svg")
                .registerRefResponse("500", new ResponseBuilder()
                        .descriptionForStatus(500)
                        .addContent("application/json", new TObject()
                                .example(new JSONObject()
                                        .put("error", "NullPointerException!")
                                        .put("detail", "Value should not be null!")
                                )
                                .addProperty("error", new TString()
                                        .example("NullPointerException")
                                        .description("Error Group")
                                )
                                .addProperty("detail", new TString()
                                        .example("Value should not be null")
                                        .description("Detailed information about the error")
                                )
                        )
                        .build()
                );
        spec.addPath("/hello/{name}", new PathBuilder()
                .addMethod(OperationType.GET, new OperationBuilder()
                        .addParameter(Parameter.builder()
                                .in(Parameter.In.PATH)
                                .name("name")
                                .description("Person to greet")
                                .schema(new TString())
                                .build()
                        )
                        .addResponse(200, new ResponseBuilder()
                                .descriptionForStatus(200)
                                .addContent("text/plain", MediaType.builder()
                                        .example("Hello World")
                                        .schema(new TString()
                                                .example("Hello World")
                                        )
                                        .build()
                                )
                                .build()
                        )
                        .addResponse(500, new Response.Ref("500"))
                        .build()
                )
                .build()
        );
        JSONObject specification = spec.build();
        System.out.println(specification.toString(2));
    }
}
```
