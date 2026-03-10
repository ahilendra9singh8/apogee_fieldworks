//package fieldworks.restcontroller;
//
//import fieldworks.dto.CurvePointDTO;
//import fieldworks.services.CurveService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/map")
//public class CurveMapController {
//
//    @Autowired
//    private CurveService curveService;
//
//    @GetMapping("/view")
//    public String viewMap() {
//        return "curve"; // /WEB-INF/jsp/curve.jsp
//    }
//
//    @GetMapping("/data")
//    @ResponseBody
//    public List<List<CurvePointDTO>> getMultiCurveData() {
//        return curveService.generateMultiCurves();
//    }
//}
//


//with file
//Testing 1
package fieldworks.restcontroller;

import fieldworks.dto.CurvePointDTO;
import fieldworks.services.CurveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/map")
public class CurveMapController {

    @Autowired
    private CurveService curveService;

    @GetMapping("/view")
    public String viewMap() {
        return "curve"; // /WEB-INF/jsp/curve.jsp
    }
    
//    @GetMapping("/live")
//    public String viewVideo() {
//        return "live"; // /WEB-INF/jsp/live.jsp
//    }

    @GetMapping("/data")
    @ResponseBody
    public List<List<CurvePointDTO>> getMultiCurveData() {
        return curveService.generateMultiCurves();
    }
}
