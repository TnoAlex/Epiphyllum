<template>
  <main>
    <!-- Container START -->
    <div class="container">
      <div class="row justify-content-center align-items-center vh-100 py-5">
        <!-- Main content START -->
        <div class="col-sm-10 col-md-8 col-lg-7 col-xl-6 col-xxl-5">
          <!-- Forgot password START -->
          <div class="card card-body rounded-3 text-center p-4 p-sm-5">
            <!-- Title -->
            <h1 class="mb-2">Forgot password?</h1>
            <p>Enter the email address associated with account.</p><!-- form START -->
            <form class="mt-3">
              <!-- New password -->
              <div class="mb-3">
                <!-- Input group -->
                <div class="input-group input-group-lg"><input class="form-control fakepassword"
                                                               type="password" id="psw-input" placeholder="Enter new password"><span
                    class="input-group-text p-0"><i
                    class="fakepasswordicon fa-solid fa-eye-slash cursor-pointer p-2 w-40px"></i></span>
                </div><!-- Pswmeter -->
                <div id="pswmeter" class="mt-2"></div>
                <div class="d-flex mt-1">
                  <div id="pswmeter-message" class="rounded"></div>
                  <!-- Password message notification -->
                  <div class="ms-auto"><i class="bi bi-info-circle ps-1" data-bs-container="body"
                                          data-bs-toggle="popover" data-bs-placement="top"
                                          data-bs-content="Include at least one uppercase, one lowercase, one special character, one number and 8 characters long."
                                          data-bs-original-title="" title=""></i></div>
                </div>
              </div><!-- Back to sign in -->
              <div class="mb-3">
                <p>Back to <a href="sign-in.html">Sign in</a></p>
              </div><!-- Button -->
              <div class="d-grid"><button type="submit" class="btn btn-lg btn-primary">Reset
                password</button></div><!-- Copyright -->
              <p class="mb-0 mt-3">©2022 <a href="/">Webestica.</a>All rights reserved</p>
            </form><!-- form END -->
          </div><!-- Forgot password END -->
        </div>
      </div><!-- Row END -->
    </div><!-- Container END -->
  </main>
</template>

<script>
export default {
  name: "Forgot-Password"
}
</script>

<style scoped>

</style>