//package project.config;
//
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.filter.HiddenHttpMethodFilter;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//import project.entities.common.SessionListener;
//
//import javax.servlet.Filter;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//
////@Order(1)
//public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class<?>[] {
//            JpaConfig.class, WebSecurityConfig.class
//        };
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class<?>[] {
//            WebConfig.class
//        };
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//
//    @Override
//    protected Filter[] getServletFilters() {
//        CharacterEncodingFilter cef = new CharacterEncodingFilter();
//        cef.setEncoding("UTF-8");
//        cef.setForceEncoding(false);
//        return new Filter[] {cef, new HiddenHttpMethodFilter()};
//    }
//
////    @Override
////    public void onStartup(ServletContext servletContext) throws ServletException {
////        super.onStartup(servletContext);
////        servletContext.addListener(new SessionListener());
////    }
//}
